<template>
  <div class="goods_hot">
    <!-- <div class="banner">
      <div class="title">人气推荐</div>
    </div>-->

    <van-tabs v-model="active" @click="onClick">
      <van-tab title="点击量最高">
        <van-list
          v-model="loading"
          :finished="finished"
          :immediate-check="false"
          finished-text="没有更多了"
          @load="getHotList"
        >
          <van-card
            v-for="(item, i) in list"
            :key="i"
            :title="item.name"
            :thumb="item.picUrl"
            :price="item.retailPrice"
            :origin-price="item.counterPrice"
            @click="itemClick(item.id)"
          ></van-card>
        </van-list>
      </van-tab>
      <van-tab title="销量最高">
        <van-list
          v-model="loading"
          :finished="finished"
          :immediate-check="false"
          finished-text="没有更多了"
          @load="getHotList"
        >
          <van-card
            v-for="(item, i) in list"
            :key="i"
            :title="item.name"
            :thumb="item.picUrl"
            :price="item.retailPrice"
            :origin-price="item.counterPrice"
            @click="itemClick(item.id)"
          ></van-card>
        </van-list>
      </van-tab>
      <van-tab title="最好评">
        <van-list
          v-model="loading"
          :finished="finished"
          :immediate-check="false"
          finished-text="没有更多了"
          @load="getHotList"
        >
          <van-card
            v-for="(item, i) in list"
            :key="i"
            :desc="item.brief"
            :title="item.name"
            :thumb="item.picUrl"
            :price="item.retailPrice"
            :origin-price="item.counterPrice"
            @click="itemClick(item.id)"
          ></van-card>
        </van-list>
      </van-tab>
      <van-tab title="本地区都在买">
        <van-list
          v-model="loading"
          :finished="finished"
          :immediate-check="false"
          finished-text="没有更多了"
          @load="getHotList"
        >
          <van-card
            v-for="(item, i) in list"
            :key="i"
            :title="item.name"
            :thumb="item.picUrl"
            :price="item.retailPrice"
            :origin-price="item.counterPrice"
            @click="itemClick(item.id)"
          ></van-card>
        </van-list>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import { recommendGoodsList } from '@/api/api';
import { Card, List, Tab, Tabs } from 'vant';
import scrollFixed from '@/mixin/scroll-fixed';

export default {
  mixins: [scrollFixed],

  data() {
    return {
      list: [],
      page: 0,
      limit: 5,
      loading: false,
      finished: false,
      active: 0
    };
  },

  created() {
    this.init();
  },

  methods: {
    init() {
      this.page = 0;
      this.list = [];
      this.getHotList();
    },
    getHotList() {
      this.page++;
      recommendGoodsList({
        recommendType: this.active,
        page: this.page,
        limit: this.limit
      }).then(res => {
        this.list.push(...res.data.data.list);
        this.loading = false;
        if(res.data.data.list.length!=1)
        this.finished = res.data.data.page >= res.data.data.pages;
      });
    },
    itemClick(id) {
      this.$router.push(`/items/detail/${id}`);
    },
    onClick(name, title) {
      this.page = 0;
      this.list = [];
      this.getHotList();
    }
  },

  components: {
    [List.name]: List,
    [Card.name]: Card,
    [Tabs.name]: Tabs,
    [Tab.name]: Tab
  }
};
</script>

<style lang="scss" scoped>
.goods_hot {
  padding: 20px;
  .banner {
    height: 250px;
    background-image: url('http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png');
    background-size: cover;
    margin-bottom: 20px;
    .title {
      text-align: center;
      line-height: 200px;
      color: #ffffff;
      font-size: 40px;
    }
  }
}
</style>