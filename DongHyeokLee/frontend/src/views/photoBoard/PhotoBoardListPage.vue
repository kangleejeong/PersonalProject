<template>
    <div id="photo-board">
        <h2>사진게시판</h2>
        <div>
        <photo-board-list :photoBoards="photoBoards"/>
        </div>
    </div>
</template>

<script>


import { mapActions, mapState } from 'vuex'
import PhotoBoardList from '@/components/photoBoard/PhotoBoardList.vue'



export default {
    name: 'PhotoBoardListPage',
    components: {
        PhotoBoardList,
        
    },
    computed: {
        ...mapState(['photoBoards'])
        
    },
    mounted () {
        if(!this.$store.state.isLogin){
            alert('로그인하세요')
            this.$router.push({name: "HomeView"});
        }else{
        this.fetchPhotoBoardList()
        }  
    },
    methods: {
        ...mapActions(['fetchPhotoBoardList'])
       
    }

}
</script>

<style scoped>

h2 {
    text-align: center;
    margin-top: 20px;
}
a {
    text-decoration: none;
    margin-left: 1230px;
}
#photo-board{
     background-image: url(@/assets/img/XL.jpg);
     background-size: cover;

}

    
</style>