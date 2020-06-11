<template>
  <div class="app-container">
    <div ref="kongtiao" class="kongtiaojifang">
      <el-button type="primary" plain @click="priceCompariosn_Test">比价参考</el-button>
      <img src="../../icons/jpg/drag.jpg"
           style="width: 20px; height: 20px"
           @mousedown="mouseDownHandleelse($event)" @mouseup="mouseUpHandleelse($event)">
      <br>
      <br>
      <div style="height:auto; width: auto" v-for="show in priceComparison.shows" :key="show">
        <el-scrollbar style="height:500px; width: 400px">
          <div>
            <div :show="show">
              <el-card style="border-radius: 10px;border-color: 	#696969">
                <el-switch
                  v-model="priceComparison.accurate"
                  active-color="#13ce66"
                  active-text="精准比价"
                  inactive-color="#909399">
                </el-switch>
                <el-button type="primary" plain @click="priceComparisonRefresh">刷新</el-button>
                <br>
                <br>
                <el-button type="info"
                           v-if="priceComparison.vo.categoryName==null || priceComparison.vo.categoryName.length==0">
                  未选择分类
                </el-button>
                <el-button type="success"
                           v-else>
                  {{priceComparison.vo.categoryName}}
                </el-button>
                <el-button type="info"
                           v-if="priceComparison.vo.commodityName==null || priceComparison.vo.commodityName.length==0">
                  未输入商品名称
                </el-button>
                <el-button type="success"
                           v-else>
                  {{priceComparison.vo.commodityName}}
                </el-button>
                <el-button :type="priceComparison.vo.commodityBrand.isEnable?'success':'info'"
                           @click="onPriceComparisonClick(priceComparison.vo.commodityBrand)">
                  {{priceComparison.vo.commodityBrand.key}}
                </el-button>
                <el-button v-for="item in priceComparison.vo.customerAttributes" :key="item"
                           :type="item.isEnable?'success':'info'" @click="onPriceComparisonClick(item)"
                           style="margin-top:5px;">
                  {{item.value}}
                </el-button>
                <!--          <template>-->
                <br>
                <br>
                <el-table
                  :data="priceComparison.comparison_merchant.data"
                  border
                  stripe
                  width="400px"
                  height="400px">
                  <el-table-column
                    v-for="info in priceComparison.comparison_merchant.keys"
                    :key="info.key"
                    :property="info.key"
                    :label="info.label">
                    <template slot-scope="scope">
                      {{scope.row[scope.column.property]}}  <!-- 渲染对应表格里面的内容 -->
                    </template>
                  </el-table-column>
                </el-table>
                <!--          </template>-->
              </el-card>
            </div>
          </div>
          <div v-if="isUpdateAction">
            <div>
              <el-card :show="show"
                       style="border-radius: 10px;border-color: #696969;width: 100%;height: 100%">
                <div id="myChart" style=" text-align: center;width: 100%;height: 400px"></div>
              </el-card>
            </div>
          </div>
        </el-scrollbar>
      </div>
    </div>

    <el-card class="box-card">
      <h3>商品介绍</h3>
      <el-form ref="goods" :rules="rules" :model="goods" label-width="150px">
        <el-form-item label="商品编号" prop="barcode">
          <el-input v-model="goods.barcode" ref="priceComparisonCommodityId"/>
        </el-form-item>
        <el-form-item label="商品名称" prop="name">
          <el-input ref="priceComparison_vo_commodityName" v-model="goods.goodsName"/>
        </el-form-item>
        <el-form-item label="市场售价" prop="counterPrice">
          <el-input v-model="goods.sellingPrice" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="税费" prop="taxes">
          <el-input v-model="goods.taxes" placeholder="0.00">
            <template slot="append">元</template>
          </el-input>
        </el-form-item>
        <el-form-item label="是否推荐" prop="isRecommand">
          <el-radio-group v-model="goods.isRecommand">
            <el-radio :label="1">是</el-radio>
            <el-radio :label="0">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否热卖" prop="isHot">
          <el-radio-group v-model="goods.isHot">
            <el-radio :label="0">普通</el-radio>
            <el-radio :label="1">热卖</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否在售" prop="isOnSale">
          <el-radio-group v-model="goods.isOnSale">
            <el-radio :label="1">在售</el-radio>
            <el-radio :label="0">未售</el-radio>
          </el-radio-group>
        </el-form-item>

        <!-- <el-form-item label="商品图片">
          <el-upload
            :action="uploadPath"
            :show-file-list="false"
            :headers="headers"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg,.jpeg,.png,.gif"
          >
            <img v-if="goods.picUrl" :src="goods.picUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item> -->

        <el-form-item label="宣传画廊">
          <el-upload
            :action="uploadPath"
            :limit="5"
            :headers="headers"
            :on-exceed="uploadOverrun"
            :on-success="handleGalleryUrl"
            :on-remove="handleRemove"
            multiple
            accept=".jpg,.jpeg,.png,.gif"
            list-type="picture-card"
          >
            <i class="el-icon-plus"/>
          </el-upload>
        </el-form-item>

        <el-form-item label="商品单位">
          <el-input v-model="goods.unit" placeholder="件 / 个 / 盒"/>
        </el-form-item>
        <!--
        <el-form-item label="关键字">
          <el-tag v-for="tag in keywords" :key="tag" closable type="primary" @close="handleClose(tag)">
            {{ tag }}
          </el-tag>
          <el-input
            v-if="newKeywordVisible"
            ref="newKeywordInput"
            v-model="newKeyword"
            class="input-new-keyword"

            @keyup.enter.native="handleInputConfirm"
            @blur="handleInputConfirm"
          />
          <el-button v-else class="button-new-keyword" type="primary" @click="showInput">+ 增加</el-button>
        </el-form-item> -->

        <el-form-item label="所属分类">
          <el-cascader ref="priceComparison_vo_categoryName" :options="categoryList" expand-trigger="hover" clearable
                       @change="handleCategoryChange"/>
        </el-form-item>

        <el-form-item label="所属品牌商">
          <el-select v-model="goods.brandId" ref="priceComparison_vo_commodityBrand" clearable>
            <el-option v-for="item in brandList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属板块">
          <el-select v-model="goods.boardId" clearable>
            <el-option v-for="item in boardList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="所属供应商">
          <el-select v-model="goods.supplierId" clearable>
            <el-option v-for="item in supplierList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="运费规则">
          <el-select v-model="goods.carriageRuleId" clearable>
            <el-option v-for="item in carriageRuleList" :key="item.value" :label="item.label" :value="item.value"/>
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="商品简介">
          <el-input v-model="goods.goodsDesc" />
        </el-form-item> -->

        <el-form-item label="商品详细介绍">
          <editor v-model="goods.goodsDesc" :init="editorInit"/>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card class="box-card">
      <h3>商品规格</h3>
      <el-row :gutter="20" type="flex" align="middle" style="padding:20px 0;">
        <el-col :span="10">
          <el-radio-group v-model="multipleSpec" @change="specChanged">
            <el-radio-button :label="false">默认标准规格</el-radio-button>
            <el-radio-button :label="true">多规格支持</el-radio-button>
          </el-radio-group>
        </el-col>
        <el-col v-if="multipleSpec" :span="10">
          <el-button :plain="true" type="primary" @click="handleSpecificationShow">添加</el-button>
        </el-col>
      </el-row>

      <el-table :data="specifications">
        <el-table-column property="specifiName" label="规格名"/>
        <el-table-column property="specifiValue" label="规格值">
          <template slot-scope="scope">
            <el-tag type="primary">
              {{ scope.row.specifiValue }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column property="picUrl" label="规格图片">
          <template slot-scope="scope">
            <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="40">
          </template>
        </el-table-column>
        <el-table-column
          v-if="multipleSpec"
          align="center"
          label="操作"
          width="250"
          class-name="small-padding fixed-width"
        >
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="handleSpecificationDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="specVisiable" title="设置规格">
        <el-form
          ref="specForm"
          :rules="rules"
          :model="specForm"
          status-icon
          label-position="left"
          label-width="100px"
          style="width: 400px; margin-left:50px;"
        >
          <el-form-item label="规格名" prop="specifiName">
            <el-input v-model="specForm.specifiName"/>
          </el-form-item>
          <el-form-item label="规格值" prop="specifiValue">
            <el-input v-model="specForm.specifiValue"/>
          </el-form-item>
          <el-form-item label="规格图片" prop="picUrl">
            <el-upload
              :action="uploadPath"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadSpecPicUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="specForm.picUrl" :src="specForm.picUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="specVisiable = false">取消</el-button>
          <el-button type="primary" @click="handleSpecificationAdd">确定</el-button>
        </div>
      </el-dialog>
    </el-card>

    <el-card class="box-card">
      <h3>商品库存</h3>
      <el-table :data="products">
        <el-table-column property="value" label="货品规格">
          <template slot-scope="scope">
            <el-tag v-for="tag in scope.row.specifications" :key="tag">
              {{ tag }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column property="price" width="100" label="货品售价"/>
        <el-table-column property="number" width="100" label="货品数量"/>
        <el-table-column property="url" width="100" label="货品图片">
          <template slot-scope="scope">
            <img v-if="scope.row.url" :src="scope.row.url" width="40">
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="100" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleProductShow(scope.row)">设置</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="productVisiable" title="添加货品">
        <el-form
          ref="productForm"
          :model="productForm"
          status-icon
          label-position="left"
          label-width="100px"
          style="width: 400px; margin-left:50px;"
        >
          <el-form-item label="货品规格列" prop="specifications">
            <el-tag v-for="tag in productForm.specifications" :key="tag">
              {{ tag }}
            </el-tag>
          </el-form-item>
          <el-form-item label="货品售价" prop="price">
            <el-input v-model="productForm.price"/>
          </el-form-item>
          <el-form-item label="货品成本价" prop="costPrice">
            <el-input v-model="productForm.costPrice"/>
          </el-form-item>
          <el-form-item label="积分最大抵扣额度" prop="maximumPoints">
            <el-input v-model="productForm.maximumPoints"/>
          </el-form-item>
          <el-form-item label="货品数量" prop="number">
            <el-input v-model="productForm.number"/>
          </el-form-item>
          <el-form-item label="货品图片" prop="url">
            <el-upload
              :action="uploadPath"
              :show-file-list="false"
              :headers="headers"
              :on-success="uploadProductUrl"
              class="avatar-uploader"
              accept=".jpg,.jpeg,.png,.gif"
            >
              <img v-if="productForm.url" :src="productForm.url" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"/>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="productVisiable = false">取消</el-button>
          <el-button type="primary" @click="handleProductEdit">确定</el-button>
        </div>
      </el-dialog>
    </el-card>

    <el-card class="box-card">
      <h3>商品参数</h3>
      <el-button type="primary" @click="handleAttributeShow">添加</el-button>
      <el-table :data="attributes">
        <el-table-column property="attribute" label="商品参数名称"/>
        <el-table-column property="value" label="商品参数值"/>
        <el-table-column align="center" label="操作" width="100" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="handleAttributeDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <el-dialog :visible.sync="attributeVisiable" title="添加商品参数">
        <el-form
          ref="attributeForm"
          :model="attributeForm"
          status-icon
          label-position="left"
          label-width="100px"
          style="width: 400px; margin-left:50px;"
        >
          <el-form-item label="商品参数名称" prop="attribute">
            <el-input v-model="attributeForm.attribute"/>
          </el-form-item>
          <el-form-item label="商品参数值" prop="value">
            <el-input v-model="attributeForm.value"/>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="attributeVisiable = false">取消</el-button>
          <el-button type="primary" @click="handleAttributeAdd">确定</el-button>
        </div>
      </el-dialog>
    </el-card>

    <div class="op-container">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handlePublish">上架</el-button>
    </div>
  </div>
</template>


<style>
  .el-card {
    margin-bottom: 10px;
  }

  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .input-new-keyword {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .avatar-uploader .el-upload {
    width: 145px;
    height: 145px;
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }

  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }

  .kongtiaojifang {
    position: fixed;
    z-index: 9999;
    top: 10%;
    right: 10%;
    cursor: pointer;
  }
</style>

<script>
  import { publishGoods, listCatAndBrand } from '@/api/goods'
  import { createStorage, uploadPath } from '@/api/storage'
  import Editor from '@tinymce/tinymce-vue'
  import { MessageBox } from 'element-ui'
  import { getToken } from '@/utils/auth'
  import { getComparisonMerchant, getComparisonHistoryData } from '@/api/pricecomparison'

  var echarts = require('echarts')

  export default {
    name: 'GoodsCreate',
    components: { Editor },

    data() {
      return {
        uploadPath,
        newKeywordVisible: false,
        newKeyword: '',
        keywords: [],
        categoryList: [],
        brandList: [],
        goods: {
          gallery: [],
          isHot: 0,
          isNew: 1,
          isOnSale: 0,
          isRecommand: 0,
          boardId: 1,
          carriageRuleId: 1,
          supplierId: 1,
          brandId: 1
        },
        specVisiable: false,
        specForm: { specifiName: '', specifiValue: '', picUrl: '' },
        multipleSpec: false,
        specifications: [{ specifiName: '规格', specifiValue: '标准', picUrl: '' }],
        productVisiable: false,
        productForm: {
          id: 0,
          specifications: [],
          price: 0.00,
          costPrice: 0.00,
          maximumPoints: 0.00,
          number: 0,
          url: ''
        },
        products: [{
          id: 0,
          specifications: ['标准'],
          price: 0.00,
          costPrice: 0.00,
          maximumPoints: 0.00,
          number: 0,
          url: ''
        }],
        attributeVisiable: false,
        attributeForm: { attribute: '', value: '' },
        attributes: [],
        rules: {
          goodsSn: [{ required: true, message: '商品编号不能为空', trigger: 'blur' }],
          name: [{ required: true, message: '商品名称不能为空', trigger: 'blur' }]
        },
        editorInit: {
          language: 'zh_CN',
          height: 500,
          convert_urls: false,
          plugins: ['advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'],
          toolbar: ['searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample', 'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'],
          images_upload_handler: function(blobInfo, success, failure) {
            const formData = new FormData()
            formData.append('file', blobInfo.blob())
            createStorage(formData).then(res => {
              success(res.data.data.url)
            }).catch(() => {
              failure('上传失败，请重新上传')
            })
          }
        },
        moveDataelse: {
          x: null,
          y: null
        },
        priceComparison: {
          shows: [],
          accurate: false,
          vo: {
            categoryName: '',
            commodityName: '',
            commodityBrand: {},
            customerAttributes: []
          },
          comparison_merchant: { keys: [], data: [{}, {}, {}, {}, {}] }
        },
        isUpdateAction: false
      }
    },
    computed: {
      headers() {
        return {
          'S-C-Admin-Token': getToken()
        }
      }
    },
    created() {
      this.init()
    },

    methods: {
      init: function() {
        listCatAndBrand().then(response => {
          this.categoryList = response.data.data.categoryList
          this.brandList = response.data.data.brandList
        })
      },
      handleCategoryChange(value) {
        this.goods.categoryId = value[value.length - 1]
      },
      handleCancel: function() {
        this.$router.push({ path: '/goods/goods' })
      },
      handlePublish: function() {
        const finalGoods = {
          goods: this.goods,
          specifications: this.specifications,
          products: this.products,
          attributes: this.attributes
        }
        console.log(finalGoods)
        publishGoods(finalGoods).then(response => {
          this.$notify.success({
            title: '成功',
            message: '创建成功'
          })
          this.$router.push({ path: '/goods/list' })
        }).catch(response => {
          MessageBox.alert('业务错误：' + response.data.errmsg, '警告', {
            confirmButtonText: '确定',
            type: 'error'
          })
        })
      },
      handleClose(tag) {
        this.keywords.splice(this.keywords.indexOf(tag), 1)
        this.goods.keywords = this.keywords.toString()
      },
      showInput() {
        this.newKeywordVisible = true
        this.$nextTick(_ => {
          this.$refs.newKeywordInput.$refs.input.focus()
        })
      },
      handleInputConfirm() {
        const newKeyword = this.newKeyword
        if (newKeyword) {
          this.keywords.push(newKeyword)
          this.goods.keywords = this.keywords.toString()
        }
        this.newKeywordVisible = false
        this.newKeyword = ''
      },
      uploadPicUrl: function(response) {
        this.goods.picUrl = response.data.url
      },
      uploadOverrun: function() {
        this.$message({
          type: 'error',
          message: '上传文件个数超出限制!最多上传5张图片!'
        })
      },
      handleGalleryUrl(response, file, fileList) {
        if (response.errno === 0) {
          console.log(response.data)
          this.goods.gallery.push(response.data.url)
        }
      },
      handleRemove: function(file, fileList) {
        for (var i = 0; i < this.goods.gallery.length; i++) {
          // 这里存在两种情况
          // 1. 如果所删除图片是刚刚上传的图片，那么图片地址是file.response.data.url
          //    此时的file.url虽然存在，但是是本机地址，而不是远程地址。
          // 2. 如果所删除图片是后台返回的已有图片，那么图片地址是file.url
          var url
          if (file.response === undefined) {
            url = file.url
          } else {
            url = file.response.data.url
          }

          if (this.goods.gallery[i] === url) {
            this.goods.gallery.splice(i, 1)
          }
        }
      },
      specChanged: function(label) {
        if (label === false) {
          this.specifications = [{ specifiName: '规格', specifiValue: '标准', picUrl: '' }]
          this.products = [{
            id: 0,
            specifications: ['标准'],
            price: 0.00,
            costPrice: 0.00,
            maximumPoints: 0.00,
            number: 0,
            url: ''
          }]
        } else {
          this.specifications = []
          this.products = []
        }
      },
      uploadSpecPicUrl: function(response) {
        this.specForm.picUrl = response.data.url
      },
      handleSpecificationShow() {
        this.specForm = { specifiName: '', specifiValue: '', picUrl: '' }
        this.specVisiable = true
      },
      handleSpecificationAdd() {
        var index = this.specifications.length - 1
        for (var i = 0; i < this.specifications.length; i++) {
          const v = this.specifications[i]
          if (v.specifiName === this.specForm.specifiName) {
            if (v.specifiValue === this.specForm.specifiName) {
              this.$message({
                type: 'warning',
                message: '已经存在规格值:' + v.specifiValue
              })
              this.specForm = {}
              this.specVisiable = false
              return
            } else {
              index = i
            }
          }
        }

        this.specifications.splice(index + 1, 0, this.specForm)
        this.specVisiable = false

        this.specToProduct()
      },
      handleSpecificationDelete(row) {
        const index = this.specifications.indexOf(row)
        this.specifications.splice(index, 1)
        this.specToProduct()
      },
      specToProduct() {
        if (this.specifications.length === 0) {
          return
        }
        // 根据specifications创建临时规格列表
        var specValues = []
        var spec = this.specifications[0].specifiName
        var values = []
        values.push(0)

        for (var i = 1; i < this.specifications.length; i++) {
          const aspec = this.specifications[i].specifiName

          if (aspec === spec) {
            values.push(i)
          } else {
            specValues.push(values)
            spec = aspec
            values = []
            values.push(i)
          }
        }
        specValues.push(values)

        var productsIndex = 0
        var products = []
        var combination = []
        var n = specValues.length
        for (var s = 0; s < n; s++) {
          combination[s] = 0
        }
        var index = 0
        var isContinue = false
        do {
          var specifications = []
          for (var x = 0; x < n; x++) {
            var z = specValues[x][combination[x]]
            specifications.push(this.specifications[z].specifiValue)
          }
          products[productsIndex] = {
            id: productsIndex,
            specifications: specifications,
            price: 0.00,
            costPrice: 0.00,
            maximumPoints: 0.00,
            number: 0,
            url: ''
          }
          productsIndex++

          index++
          combination[n - 1] = index
          for (var j = n - 1; j >= 0; j--) {
            if (combination[j] >= specValues[j].length) {
              combination[j] = 0
              index = 0
              if (j - 1 >= 0) {
                combination[j - 1] = combination[j - 1] + 1
              }
            }
          }
          isContinue = false
          for (var p = 0; p < n; p++) {
            if (combination[p] !== 0) {
              isContinue = true
            }
          }
        } while (isContinue)

        this.products = products
      },
      handleProductShow(row) {
        this.productForm = Object.assign({}, row)
        this.productVisiable = true
      },
      uploadProductUrl: function(response) {
        this.productForm.url = response.data.url
      },
      handleProductEdit() {
        for (var i = 0; i < this.products.length; i++) {
          const v = this.products[i]
          if (v.id === this.productForm.id) {
            this.products.splice(i, 1, this.productForm)
            break
          }
        }
        this.productVisiable = false
      },
      handleAttributeShow() {
        this.attributeForm = {}
        this.attributeVisiable = true
      },
      handleAttributeAdd() {
        this.attributes.unshift(this.attributeForm)
        this.attributeVisiable = false
      },
      handleAttributeDelete(row) {
        const index = this.attributes.indexOf(row)
        this.attributes.splice(index, 1)
      },
      mouseDownHandleelse(event) {
        this.moveDataelse.x = event.pageX - this.$refs.kongtiao.offsetLeft
        this.moveDataelse.y = event.pageY - this.$refs.kongtiao.offsetTop
        event.currentTarget.style.cursor = 'move'
        window.onmousemove = this.mouseMoveHandleelse
      },
      mouseMoveHandleelse(event) {
        var moveLeft = event.pageX - this.moveDataelse.x + 'px'
        var moveTop = event.pageY - this.moveDataelse.y + 'px'
        this.$refs.kongtiao.style.left = moveLeft
        this.$refs.kongtiao.style.top = moveTop
      },
      mouseUpHandleelse(event) {
        window.onmousemove = null
        event.currentTarget.style.cursor = 'move'
        console.log('鼠标松开了')
      },
      onPriceComparisonClick(obj) {
        obj.isEnable = !obj.isEnable
        this.priceComparison.vo.customerAttributes.push({})
        this.priceComparison.vo.customerAttributes.pop()
      },
      priceComparisonRefresh() {
        this.priceComparison.shows.pop()
        this.priceCompariosn_Test()
      },
      priceCompariosn_Test() {
        if (this.priceComparison.shows.length === 0) {
          var goodNameInput = this.$refs.priceComparison_vo_commodityName
          var categoryNameOption = this.$refs.priceComparison_vo_categoryName
          var goodName = goodNameInput.value
          var categoryName = ''
          if (categoryNameOption.getCheckedNodes()!=undefined &&
            categoryNameOption.getCheckedNodes() != null &&
            categoryNameOption.getCheckedNodes().length>0){
            categoryName = categoryNameOption.getCheckedNodes()[0].label
          }
          if (goodName === undefined || goodName == null || goodName.length === 0) {
            // goodNameInput.focus()
            goodName = ''
            // this.alertDia('商品名称不能为空')
          }
          if (categoryName === undefined || categoryName == null || categoryName.length === 0) {
            // categoryNameOption.focus()
            categoryName = ''
            // this.alertDia('商品名称不能为空')
          }
          if (true) {
            var listQuery = this.getListQuery()
            console.log('+++++++')
            console.log(listQuery)
            console.log('+++++++')
            this.getComparisonMerchant(listQuery)
            this.priceComparison.shows.push({})
          }
          if (this.isUpdateAction) {
            var commodityId = this.$refs.priceComparisonCommodityId.value
            this.getComparisonHistoryData(commodityId)
          }
        } else {
          this.priceComparison.shows.pop()
        }
      },
      getListQuery() {
        var goodNameInput = this.$refs.priceComparison_vo_commodityName
        var categoryNameOption = this.$refs.priceComparison_vo_categoryName
        var commodityBrandOption = this.$refs.priceComparison_vo_commodityBrand
        var goodName = goodNameInput.value
        var categoryName = ''
        if (categoryNameOption.getCheckedNodes()!=undefined &&
          categoryNameOption.getCheckedNodes() != null &&
          categoryNameOption.getCheckedNodes().length>0){
          categoryName = categoryNameOption.getCheckedNodes()[0].label
        }
        var commodityBrand = commodityBrandOption.selectedLabel
        this.priceComparison.vo.commodityName = goodName
        this.priceComparison.vo.categoryName = categoryName
        this.priceComparison.vo.commodityBrand.key = commodityBrand
        this.priceComparison.vo.customerAttributes = this.attributes
        var accurate = this.priceComparison.accurate
        var brand = null
        if (this.priceComparison.vo.commodityBrand.isEnable) {
          brand = this.priceComparison.vo.commodityBrand.key
        }
        var attrs = {}
        var originAttrs = this.priceComparison.vo.customerAttributes
        for (var index = 0; index < originAttrs.length; index++) {
          if (originAttrs[index].isEnable) {
            attrs[originAttrs[index]['attribute']] = originAttrs[index]['value']
          }
        }
        var listQuery = {
          'accurate': accurate,
          'categoryName': categoryName,
          'commodityName': goodName,
          'commodityBrand': brand,
          'customerAttributes': attrs
        }
        return listQuery
      },
      getComparisonMerchant(listQuery) {
        console.log(listQuery)
        getComparisonMerchant(listQuery).then(res => {
          var list = res.data.data
          console.log(list)
          this.priceComparison.comparison_merchant.data = [{}, {}, {}, {}, {}]
          this.priceComparison.comparison_merchant.keys = []
          var merchantData = this.priceComparison.comparison_merchant.data
          var keys = this.priceComparison.comparison_merchant.keys
          keys.push({ 'label': null, 'key': '_0' })
          merchantData[0]['_0'] = '最高价'
          merchantData[1]['_0'] = '最低价'
          merchantData[2]['_0'] = '最多定价'
          merchantData[3]['_0'] = '平均价'
          merchantData[4]['_0'] = '销量最高价'
          for (var i = 1; i <= list.length; i++) {
            if (list[i - 1].valid === true) {
              // keys.push(list[i].platformName)
              keys.push({ 'label': list[i - 1].platformName, 'key': '_' + i })
              merchantData[0]['_' + i] = list[i - 1].maxPrice
              merchantData[1]['_' + i] = list[i - 1].minPrice
              merchantData[2]['_' + i] = this.priceComparisonRemoveBracket(list[i - 1].mostPrice)
              merchantData[3]['_' + i] = list[i - 1].averagePrice
              merchantData[4]['_' + i] = this.priceComparisonRemoveBracket(list[i - 1].mostSalePrice)
            }
          }
          console.log('aaaaa' + merchantData)
          console.log('bbbbb' + keys)
        }).catch(() => {
          console.log()
        })
      },
      priceComparisonRemoveBracket(value) {
        var a = JSON.stringify(value)
        if (a.substring(0, 1) === '[' && a.length > 2) {
          a = a.substring(1, a.length - 1)
        } else {
          a = '无信息'
        }
        return a
      },
      getMyDays(d1, d2) {
        // var d = [{year:'', month: null,day:null, hour:null, minute:null, second: null},
        //   {year:'', month: null,day:null, hour:null, minute:null, second: null}]
        var start = new Date(d1)
        var end = new Date(d2)
        var s1 = parseInt(start.getTime()), s2 = parseInt(end.getTime())
        var day = (s2 - s1) / (24 * 60 * 60 * 1000)
        day = Math.ceil(day)
        return day
      },
      getAvgDaySale(d1, d2) {
        var data = d1 / d2
        var f = Math.round(data * 100) / 100
        return f
      },
      getComparisonHistoryData(id) {
        getComparisonHistoryData(id).then(res => {
          console.log(res.data.data)
          var chartData = res.data.data
          var xData = []
          var yData = []
          var yData2 = []
          var days = []
          var avgDaySale = []
          var label = {}
          var temp = { price: null, sale: null }
          var curDate = new Date()
          var strDate = curDate.getFullYear() + '-' +
            ((parseInt(curDate.getMonth()) + 1).toString().length == 1 ? '0' + (parseInt(curDate.getMonth()) + 1).toString() : (parseInt(curDate.getMonth()) + 1).toString()) + '-' +
            (curDate.getDate().length == 1 ? '0' + curDate.getDate() : curDate.getDate()) + ' ' +
            (curDate.getHours().length == 1 ? '0' + curDate.getHours() : curDate.getHours()) + ':' +
            (curDate.getMinutes().length == 1 ? '0' + curDate.getMinutes() : curDate.getMinutes()) + ':' +
            (curDate.getSeconds().length == 1 ? '0' + curDate.getSeconds() : curDate.getSeconds())
          var tIndex = chartData.length
          chartData.push({})
          chartData[tIndex].updateTime = strDate
          chartData[tIndex].updatePrice = chartData[tIndex - 1].updatePrice
          chartData[tIndex].saleVolume = '0'
          for (var i = 0; i < chartData.length; i++) {
            xData.push(chartData[i].updateTime)
            temp.price = chartData[i].updatePrice
            temp.sale = chartData[i].saleVolume
            yData.push(chartData[i].updatePrice)
            yData2.push(temp.sale)
            var nextDate
            if (i == chartData.length - 1) {
              nextDate = new Date()
            } else {
              nextDate = chartData[i + 1].updateTime
            }
            days.push(this.getMyDays(chartData[i].updateTime, nextDate))
            avgDaySale.push(this.getAvgDaySale(yData2[i], days[i]))
            // yData.push(temp)
          }
          label.maxPrice = Math.max.apply(null, yData)
          label.minPrice = Math.min.apply(null, yData)
          label.maxDay = Math.max.apply(null, days)
          label.maxDaySale = Math.max.apply(null, avgDaySale)
          label.curPrice = yData[yData.length - 1]
          label.yStart = Math.ceil(label.minPrice) > 10 ? Math.floor(label.minPrice) - 10 : 0
          label.yEnd = Math.ceil(label.maxPrice) + 10
          var myChart = echarts.init(document.getElementById('myChart'), 'light')
          // 绘制图表
          myChart.setOption({
            title: {
              text: '历史价格走势',
              subtext: '最高价：' + label.maxPrice + '  ' + '最低价：' + label.minPrice + '\n' +
                '最大保持天数：' + label.maxDay + '  ' + '最高日均销量：' + label.maxDaySale + '  ' +
                '当前价格：' + label.curPrice,
              // x: 'center',
              y: 'top',
              // textAlign: 'center',
              left: 'center'
            },
            tooltip: {
              trigger: 'axis',
              formatter: function(params) {
                console.log(params)
                return '价格 ' + params[0].value + '元<br>' +
                  '销量 ' + params[1].value + '<br>' +
                  '时间 ' + params[0].name + '<br>' +
                  '保持 ' + params[2].value + '天<br>' +
                  '日销 ' + params[3].value
              }
            },
            legend: {
              data: 'null'
            },
            xAxis: {
              name: '时间线',
              data: xData
              // data: this.historySaleDate
            },
            yAxis: {
              name: '价格/元',
              type: 'value',
              min: label.yStart,
              max: label.yEnd
            },
            series: [
              {
                name: '价格/元',
                type: 'line',
                data: yData
                // data: this.historySaleAmount
              },
              {
                symbolSize: 0, // symbol的大小设置为0
                showSymbol: false, // 不显示symbol
                lineStyle: {
                  width: 0, // 线宽是0
                  color: 'rgba(0, 0, 0, 0)' // 线的颜色是透明的
                },
                name: 'qty',
                type: 'line',
                data: yData2
                // data: this.historySaleAmount
              },
              {
                symbolSize: 0, // symbol的大小设置为0
                showSymbol: false, // 不显示symbol
                lineStyle: {
                  width: 0, // 线宽是0
                  color: 'rgba(0, 0, 0, 0)' // 线的颜色是透明的
                },
                name: 'days',
                type: 'line',
                data: days
                // data: this.historySaleAmount
              },
              {
                symbolSize: 0, // symbol的大小设置为0
                showSymbol: false, // 不显示symbol
                lineStyle: {
                  width: 0, // 线宽是0
                  color: 'rgba(0, 0, 0, 0)' // 线的颜色是透明的
                },
                name: 'daySale',
                type: 'line',
                data: avgDaySale
                // data: this.historySaleAmount
              }
            ]
          })
          // var myChart = this.$echarts.init(this.$refs.myChart)
          /*myChart.setOption({
            title: {text: '历史数据记录表' },
            toolbox: {
              show: true,
              feature: {
                saveAsImage: {
                  show: true
                }
              }
            },
            xAxis: {
              data: xData
            },
            yAxis: {},
            series: [{
              name: '价格',
              type: 'line',
              data: yData
            }]
          })*/
        }).catch(() => {
          console.log()
        })
      }
    }
  }
</script>
