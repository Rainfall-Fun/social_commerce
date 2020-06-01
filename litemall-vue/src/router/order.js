const Tabbar = () => import('@/components/Tabbar/');
const CommentGoods = () => import('@/views/user/comment-goods')

export default [
  {
    path: '/order',
    name: 'cart',
    meta: {
      login1: true
    },
    components: { 
      default: () => import('@/views/order/tabbar-cart'), 
      tabbar: Tabbar 
    }
  },
  {
    path: '/order/checkout',
    name: 'orderCheckout',
    component: () => import('@/views/order/checkout')
  },
  {
    path: '/order/order-detail',
    name: 'orderDetail',
    component: () => import('@/views/order/order-detail')
  },
  {
    path: '/order/payment',
    name: 'payment',
    component: () => import('@/views/order/payment')
  },
  {
    path: '/order/payment/:status',
    name: 'paymentStatus',
    component: () => import('@/views/order/payment-status'),
    props: true
  },
  {
    path: '/order/comment/:orderId',
    name: 'commentGoods',
    component: CommentGoods
  }
];
