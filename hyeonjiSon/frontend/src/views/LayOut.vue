<template>
  <v-app id="inspire">
    <v-app-bar app clipped-right color="blue" dark>
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>
        <div @click="home">로고 들어갈 자리</div>
      </v-toolbar-title>
      <v-spacer></v-spacer>

      <v-toolbar-items v-if="this.$store.state.userInfo == null">
              <v-btn onclick="location.href='http://localhost:8080/memberLoginPage'">
                <v-icon>
                    mdi-login
                </v-icon>
            </v-btn>
            <v-btn onclick="location.href='http://localhost:8080/memberRegisterPage'">
                    <v-icon>
                        mdi-account-multiple-plus-outline
                    </v-icon>
            </v-btn>            
      </v-toolbar-items>
      <v-toolbar-items v-if="this.$store.state.userInfo != null">
            <v-btn @click="logout">
                <v-icon>
                    mdi-logout
                </v-icon>
            </v-btn>
            <v-btn @click="mypage">
                <v-icon>
                    mdi-clipboard-account-outline
                </v-icon>
            </v-btn>     
      </v-toolbar-items>

      <slot name="menubar"></slot>
    </v-app-bar>

<!-- navigation 메뉴들을 v-for 배열 활용하여 깔끔하게 정리  -->
    <v-navigation-drawer v-model="drawer" app>
      <v-list dense>
        <v-list-item v-for="navrouter in navrouters" :key="navrouter.name" router :to="navrouter.route">
          <v-list-item-action>
            <v-icon left> {{ navrouter.icon }} </v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title> {{ navrouter.text }} </v-list-item-title>
          </v-list-item-content>
        </v-list-item>

      </v-list>
    </v-navigation-drawer>

    <v-container>
      <v-content id="content">
        <router-view/>
      </v-content>
    </v-container>
  </v-app>
</template>

<script>
import router from '@/router'

import { mapState } from 'vuex'
import Vue from 'vue'
import cookies from 'vue-cookies'
Vue.use(cookies)

export default {
  name: "LayOut",
    props: {
      member: {
          type: Object,
          require: true
      }
    },
data () {
    return {
        isLogin: false,
        loginInfo: JSON.parse(localStorage.getItem('loginInfo')),
        drawer: false,
        left: false,

        navrouters: [
          { icon: 'mdi-leaf-circle-outline', text: '프로젝트 소개', name: 'home', route: '/projectIntroducePage' },
          { icon: 'mdi-recycle-variant', text: '분리수거 안내', name: 'home', route: '/recycleGuideMainPage' },
          { icon: 'mdi-note-edit-outline', text: '생활 정보 공유', name: 'home', route: '/livingTipBoardPage' },
          { icon: 'mdi-store-marker-outline', text: '나눔 받는 가게', name: 'home', route: '/boardStorePage' },
          { icon: 'mdi-hand-heart-outline', text: '기부 정보', name: 'home', route: '/donationInfoPage' },
          { icon: 'mdi-email-send-outline', text: '메일 제보', name: 'home', route: '/emailReportPage' }
        ]
    }
  },
  mounted () {
        this.$store.state.userInfo = this.$cookies.get("user")
        if (this.$store.state.userInfo != null){
            this.$store.state.isLogin = true
        }else {
            this.$store.state.isLogin = false
        }
  },
  computed: {
        ...mapState(['isLogin']),
        ...mapState(['session'])      
  },
  methods: {
    home () {
      (window.location.pathname !== '/') ? router.push('/') : router.go(0)
    },
    logout () {
      this.$cookies.remove("user")
      this.isLogin = false
      this.$store.state.userInfo = null
      alert('로그아웃 성공!')
    },
    mypage(){
      this.$router.push({ name: 'memberMyPage', params: {memberNo: this.$store.state.userInfo.memberNo.toString()} }) 
    }
  }
}
</script>

<style scoped>
  a { text-decoration: none; } 
</style>