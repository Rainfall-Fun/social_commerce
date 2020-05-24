<template>
<div>
  <div class="title">商家其它商品</div>
  <van-list v-model="loading"
              :finished="finished"
              :immediate-check="false"
              finished-text="没有更多了"
              @load="getGoodsList">
      <van-card v-for="(item, i) in list"
                :key="i"
                :title="item.name"
                :thumb="item.picUrl"
                :price="item.retailPrice"
                :origin-price="item.counterPrice"
                @click="itemClick(item.id)">
      </van-card>
    </van-list>
</div>
  
</template>

<script>
import { Card, List } from 'vant';
import scrollFixed from '@/mixin/scroll-fixed';
import { supplierRecommend } from '@/api/api';

export default {
  mixins: [scrollFixed],
  data() {
    return {
      list: [],
      page: 0,
      limit: 10,
      loading: false,
      finished: false
    };
  },
  created() {
    this.init();
  },
  methods: {
    init() {
      this.page = 0;
      this.list = [];
      this.getGoodsList();
    },
    getGoodsList() {
      this.page++;
      supplierRecommend({
        page: this.page,
        limit: this.limit
      }).then(res => {
        this.list.push(...res.data.data.list);
        this.loading = false;
        this.finished = res.data.data.page >= res.data.data.pages;
      })
    },
    itemClick(id) {
      this.$router.push(`/items/detail/${id}`);
    }
  },
    components: {
    [List.name]: List,
    [Card.name]: Card
  }
};
</script>