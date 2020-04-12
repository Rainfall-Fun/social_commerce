<template>
	<md-field-group>
	</md-field-group>
</template>

<script>
import field from '@/components/field/';
import fieldGroup from '@/components/field-group/';
import { mobileReg } from '@/utils/validate';
import { authLogin } from '@/api/api';
import { setLocalStorage } from '@/utils/local-storage';

export default {
  data() {
    return {
      mobile: ''
    };
  },
  created(){

    // authLogin({code: '123456'}).then(res=>{
    //     console.log(res);
    //   }).catch(error=>{

    //   })


    if(this.$route.query.code==undefined) { 
      let _nowUrl = window.location.href.split('?')[0];
      var reg=new RegExp("#");
      let _shareUrl = `https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2067649058788a6d&redirect_uri=${encodeURIComponent(_nowUrl.replace(reg,""))}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect`;
      window.location.href = _shareUrl;
      return
    }else{
      authLogin({code: this.$route.query.code}).then(res=>{
        console.log(res.data.data);
        setLocalStorage({
          Authorization: res.data.data.token,
          userName:res.data.data.userName
        });
      }).catch(error=>{
        console.log(error);
      })
    }
  },
  methods: {
  },

  components: {
  }
};
</script>

