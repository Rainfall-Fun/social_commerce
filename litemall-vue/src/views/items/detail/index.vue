<template>
  <div class="item_detail">
    <van-swipe :autoplay="3000">
      <van-swipe-item v-for="(image, index) in goods.info.gallery" :key="index">
        <img v-lazy="image" width="100%">
      </van-swipe-item>
    </van-swipe>
    <van-cell-group class="item_cell_group" v-if="goods">
      <van-cell class="item_info">
        <div>
          <span class="item_price">{{ goods.info.price*100 | yuan }}</span>
          <span class="item_market_price">{{goods.info.price*150 | yuan}}</span>
        </div>
        <div class="item-title">
          {{ goods.info.goodsName }}
        </div>
        <div class="item_intro">{{goods.info.brief}}</div>
      </van-cell>
    </van-cell-group>

  <div class="item_cell_group">
    <van-cell-group>
      <van-cell
        title="规格"
        isLink
        value="请选择"
        @click.native="skuClick"
      />
      <van-cell title="属性" isLink @click.native="propsPopup = true"/>
      <van-cell title="运费" value="满88免邮费"/>
      <van-cell title="淘口令" @click="copy" value="点击复制淘口令"/> 
    </van-cell-group>
    <van-sku
      v-model="showSku"
      :sku="sku"
      :hide-stock="true"
      :goods="skuGoods"
      :goodsId="goods.info.id"
      :close-on-click-overlay="true"
      @buy-clicked="buyGoods"
      @add-cart="addCart"
    />
    <van-popup v-model="propsPopup" position="bottom">
      <popup-props :propsStr="props_str"></popup-props>
    </van-popup>
  </div>

    <div class="item_desc">
      <div class="item_desc_title">商品详情</div>
      <div class="item_desc_wrap" v-if="goods.info.goodsDesc" v-html="goods.info.goodsDesc"></div>
      <div class="item_desc_wrap" v-else style="text-align: center;">
        <p>无详情</p>
      </div>
    </div>

    <van-goods-action>
      <van-goods-action-icon @click="toCart" icon="cart-o" :info="(cartInfo > 0) ? cartInfo : ''"/>
      <van-goods-action-icon @click="addCollect" icon="star-o" :style="(goods.userHasCollect !== 0) ? 'color: #f7b444;':''"/>
      <van-goods-action-button type="warning" @click="skuClick" text="加入购物车"/>
      <van-goods-action-button type="danger" @click="skuClick" text="立即购买"/>
    </van-goods-action>

  </div>
</template>

<script>

import { goodsDetail, cartGoodsCount, collectAddOrDelete, cartAdd, cartFastAdd } from '@/api/api';

import { Sku, Swipe, SwipeItem, GoodsAction, GoodsActionButton, GoodsActionIcon, Popup } from 'vant';
import { setLocalStorage } from '@/utils/local-storage';
import popupProps from './popup-props';
import _ from 'lodash';

export default {
  props: {
    itemId: [String, Number]
  },

  data() {
    const isLogin = !!localStorage.getItem('Authorization');

    return {
      isLogin,
      goods: {
        userHasCollect: 0,
        info: {
          gallery: []
        }
      },
      sku: {
        tree: [],
        list: [],
        price: '1.00' // 默认价格（单位元）
      },
      skuGoods: {
        // 商品标题
        title: '',
        // 默认商品 sku 缩略图
        picture: ''
      },
      cartInfo: 0,
      selectSku: {
        selectedNum: 1,
        selectedSkuComb: {
          sku_str: 'aa'
        }
      },
      propsPopup: false,
      showSku: false
    };
  },

  computed: {
    props_str() {
      let props_arr = [];
      _.each(this.goods.attribute, json => {
        props_arr.push([json['attribute'], json['value']]);
      });
      return props_arr || [];
    }
  },

  created() {
    this.initData();
  },

  methods: {
    copy(){
      var toast=this.$toast;
      var text=window.btoa('goods id is:'+this.itemId);
      var text1=window.atob(text);
      var password='打开社交小商城，查看好友推荐的商品('+text+')';
      this.$copyText(password).then(
        function(e) {
          toast({
            message: '复制成功',
            duration: 1500
          });
        },
        function(e) {
          console.log("copy arguments e:", e);
          alert("复制失败!");
        }
      );
    },
    skuClick() {
      this.showSku = true;
    },
    initData() {
      goodsDetail({ id: this.itemId }).then(res => {
        this.goods = res.data.data;
        this.skuAdapter();
      });

      cartGoodsCount().then(res => {
        this.cartInfo = res.data.data;
      });
    },
    toCart() {
      this.$router.push({
        name: 'cart'
      });
    },
    addCollect() {
      collectAddOrDelete({ valueId: this.itemId, type: 0 }).then(res => {
        if (this.goods.userHasCollect === 1) {
          this.goods.userHasCollect = 0;
        } else {
          this.goods.userHasCollect = 1;
          this.$toast({
            message: '收藏成功',
            duration: 1500
          });
        }
      });
    },
    getProductId(s1, s2) {
      var productId;
      var s1_name;
      var s2_name;
      _.each(this.goods.specificationList, specification => {
        _.each(specification.valueList, specValue => {
          if (specValue.goodsSpecifiId === s1) {
            s1_name = specValue.specifiValue;
          } else if (specValue.goodsSpecifiId === s2) {
            s2_name = specValue.specifiValue;
          }
        });
      });
      _.each(this.goods.productList, v => {
        let result = _.without(v.specifications, s1_name, s2_name);
        if (result.length === 0) {
          productId = v.id;
        }
      });
      return productId;
    },
    getProductIdByOne(s1) {
      var productId;
      _.each(this.goods.productList, v => {
        let result = _.without(v.specifications, s1);
        if (result.length === 0) {
          productId = v.id;
        }
      });
      return productId;
    },
    addCart(data) {
      let that = this;
      let params = {
        goodsId: this.goods.info.goodsId,
        number: data.selectedNum,
        id: 0
      };
      if (_.has(data.selectedSkuComb, 's3')) {
        this.$toast({
          message: '目前仅支持两规格',
          duration: 1500
        });
        return;
      } else if (_.has(data.selectedSkuComb, 's2')) {
        params.id = this.getProductId(
          data.selectedSkuComb.s1,
          data.selectedSkuComb.s2
        );
      } else {
        params.id = this.getProductIdByOne(data.selectedSkuComb.s1);
      }
      cartAdd(params).then(() => {
        this.cartInfo = this.cartInfo + data.selectedNum;
        this.$toast({
          message: '已添加至购物车',
          duration: 1500
        });
        that.showSku = false;
      });
    },
    buyGoods(data) {
      let that = this;
      let params = {
        goodsId: this.goods.info.goodsId,
        number: data.selectedNum,
        productId: 0
      };
      if (_.has(data.selectedSkuComb, 's3')) {
        this.$toast({
          message: '目前仅支持两规格',
          duration: 1500
        });
        return;
      } else if (_.has(data.selectedSkuComb, 's2')) {
        params.productId = this.getProductId(
          data.selectedSkuComb.s1,
          data.selectedSkuComb.s2
        );
      } else {
        params.productId = this.getProductIdByOne(data.selectedSkuComb.s1);
      }
      let list=[];
      list.push(params);
      this.$store.commit('putPurchaseList',{
        list:list
      });
      this.$router.push('/order/checkout');
      // cartFastAdd(params).then(res => {
      //   let cartId = res.data.data;
      //   setLocalStorage({ CartId: cartId });
      //   that.showSku = false;
      //   this.$router.push('/order/checkout');
      // });
    },
    skuAdapter() {
      const tree = this.setSkuTree();
      const list = this.setSkuList();
      const skuInfo = {
        price: parseInt(this.goods.info.retailPrice), // 未选择规格时的价格
        stock_num: 0, // TODO 总库存
        collection_id: '', // 无规格商品skuId取collection_id，否则取所选sku组合对应的id
        none_sku: false, // 是否无规格商品
        hide_stock: true
      };
      this.sku = {
        tree,
        list,
        ...skuInfo
      };
      this.skuGoods = {
        title: this.goods.info.name,
        picture: this.goods.info.picUrl
      };
    },
    setSkuList() {
      var sku_list = [];
      _.each(this.goods.productList, v => {
        var sku_list_obj = {};
        _.each(v.specifications, (specifiName, index) => {
          sku_list_obj['s' + (~~index + 1)] = this.findSpecValueIdByName(
            specifiName
          );
        });

        sku_list_obj.price = v.price * 100;
        sku_list_obj.stock_num = v.number;
        sku_list.push(sku_list_obj);
      });

      return sku_list;
    },
    setSkuTree() {
      let that = this;
      let specifications = [];
      _.each(this.goods.specificationList, (v, k) => {
        let values = [];
        _.each(v.valueList, vv => {
          vv.name = vv.value;
          values.push({
            id: vv.goodsSpecifiId,
            name: vv.specifiValue,
            imUrl: vv.url
          });
        });
        specifications.push({
          k: v.name,
          v: values,
          k_s: 's' + (~~k + 1)
        });
      });
      return specifications;
    },
    findSpecValueIdByName(name) {
      let id = 0;
      _.each(this.goods.specificationList, specification => {
        _.each(specification.valueList, specValue => {
          if (specValue.specifiValue === name) {
            id = specValue.goodsSpecifiId;
            return;
          }
        });
        if (id !== 0) {
          return;
        }
      });
      return id;
    }
  },

  components: {
    [Popup.name]: Popup,
    [Swipe.name]: Swipe,
    [SwipeItem.name]: SwipeItem,
    [Sku.name]: Sku,
    [GoodsAction.name]: GoodsAction,
    [GoodsActionButton.name]: GoodsActionButton,
    [GoodsActionIcon.name]: GoodsActionIcon,
    [popupProps.name]: popupProps
  }
};
</script>

<style lang="scss" scoped>
.item_detail {
  img {
    max-width: 100%;
  }
}

.item_cell_group {
  margin-bottom: 15px;
}

.item_price {
  font-size: 20px;
  color: $red;
  margin-right: 10px;
}

.item_market_price {
  color: $font-color-gray;
  text-decoration: line-through;
  font-size: $font-size-small;
}

.item-title {
  line-height: 1.4;
}

.item_dispatch {
  font-size: $font-size-small;
  color: $font-color-gray;
}

.item_intro {
  line-height: 18px;
  margin: 5px 0;
  font-size: $font-size-small;
  color: $font-color-gray;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
}

.item_desc {
  background-color: #fff;
  /deep/ p {
    padding: 0 10px;
    margin-block-start: 0 !important;
    margin-block-end: 0 !important;
  }
  /deep/ img {
    max-width: 100%;
    display: block;
  }
}

.item_desc_title {
  @include one-border;
  padding: 10px 0;
  text-align: center;
}
</style>
