import Vue from 'vue'
import VueRouter from 'vue-router'
import VuetifyTemplateList from '@/views/VuetifyTemplateList.vue'
import HomeView from '@/views/MainPage/HomeView.vue'
import K1CheckBrandView from '@/views/K1CheckBrandVeiw/K1CheckBrandView.vue'
import K1CheckBrandReadPage from '@/views/K1CheckBrandVeiw/K1CheckBrandReadPage.vue'
import K1QuestionReadPage from '@/views/K1QuestionVeiw/K1QuestionReadPage.vue'
import K1QuestionView from '@/views/K1QuestionVeiw/K1QuestionView.vue'
import K1CheckBrandRegisterPage from '@/views/K1CheckBrandVeiw/K1CheckBrandRegisterPage.vue'
import K1CheckBrandModifyPage from '@/views/K1CheckBrandVeiw/K1CheckBrandModifyPage.vue'
import K1QuestionRegisterPage from '@/views/K1QuestionVeiw/K1QuestionRegisterPage'
import K2ProductView from '@/views/K2ProductView/K2ProductView'
import K1QuestionModifyPage from '@/views/K1QuestionVeiw/K1QuestionModifyPage.vue'
import MyPage from '@/views/member/MyPage'

Vue.use(VueRouter)

const routes = [
  { 
    path: '/test',
  name:'VuetifyTemplateList',
  component:VuetifyTemplateList
  },
  {
    path: '/',
    name: 'homeView',
    component: HomeView
  },
  {
    path: '/K1CheckBrandView',
    name: 'K1CheckBrandView',
    component: K1CheckBrandView
  },
  {
    path:'/K1CheckBrandReadPage/:boardNo',
    name:'K1CheckBrandReadPage',
    components: {
      default: K1CheckBrandReadPage
  },
  props: {
    default: true
  }
},
  {
    path:'/K1QuestionReadPage/:boardNo',
    name:'K1QuestionReadPage',
    components: {
      default: K1QuestionReadPage
  },
  props: {
    default: true
  }
  },
  {
  path:'/K1QuestionView',
  name:'K1QuestionView',
  component: K1QuestionView
},
{
  path:'/K1CheckBrandRegisterPage',
  name:'K1CheckBrandRegisterPage',
  component:K1CheckBrandRegisterPage
},
{
  path:'/K1CheckBrandModifyPage/:boardNo',
  name:'K1CheckBrandModifyPage',
  components:{
    default:K1CheckBrandModifyPage
},
props:{
  default: true
}
},
{
  path:'/K1QuestionRegisterPage',
  name:'K1QuestionRegisterPage',
  component:K1QuestionRegisterPage
},
{
  path:'/K2ProductView',
  name:'K2ProductView',
  component:K2ProductView
},
{
  path: '/MyPage',
  name: 'MyPage',
  component: MyPage
},
{
path: '/K1QuestionModifyPage/:boardNo',
  name: 'K1QuestionModifyPage',
  components:{
    default:K1QuestionModifyPage
},
props:{
  default: true
}
},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
