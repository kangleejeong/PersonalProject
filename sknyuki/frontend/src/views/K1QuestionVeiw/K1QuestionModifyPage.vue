<template>
    <v-container>
        <header-view></header-view>
        <br>
        <drop-down></drop-down>
           <div align="center">
      
      <question-board-modify v-if="QuestionBoard" :QuestionBoard="QuestionBoard" 
@submitContents="modifyContentsSubmit" 
@submitFiles="modifyFilesSubmit"/>
<p v-else>로딩중 .....</p>
           </div>
        </v-container>
</template>
<script>
import HeaderView from '@/components/home/headerView.vue'
import DropDown from '@/components/KategoriePage1/DropDown.vue'
import QuestionBoardModify from '@/components/QuestionBoard/QuestionBoardModify.vue'
import axios from 'axios'
import { mapActions, mapState } from 'vuex'


export default {
    name:'K1QuestionModifyPage',
    components:{ 
    HeaderView,
    DropDown,
    QuestionBoardModify,
    },
    props:{
         boardNo: {
            type: String,
            required: true
         }
        },
        data () {
    return {
      files:[],
        }
    },
         computed: {
      ...mapState(['QuestionBoard'])
    },
          created () {
            console.log(this.boardNo)
        this.fetchQuestionBoard(this.boardNo)
                .catch(() => {
                    alert('게시물 DB 조회 실패!')
                    this.$router.back()
                    
                })
    },
   

    methods:{
        ...mapActions(['fetchQuestionBoard']),
        modifyContentsSubmit (payload) {
            const { title, content } = payload

            axios.put(`http://localhost:7777/QuestionBoard/${this.boardNo}`,
                { title, writer: this.QuestionBoard.writer, content, regDate: this.BrandCheckBoard.regDate })
                    .then(() => {
                        alert('게시물 수정 성공!')
                    })
                    .catch(() => {
                        alert('게시물 수정 실패!')
                    })
        },
   
 
         modifyFilesSubmit(files){
           this.files=files
             console.log('수정한 사진이 넘어왔습니다'+files)
     setTimeout(()=>{
                    const formData=new FormData()
                    for (let i=0; i<this.files.length; i++){
                        formData.append('fileList',this.files[i])
                    }

                    formData.append('boardNo',this.boardNo)
                    //formData.append('id', this.id)
                    console.log(this.boardNo)
                    

                    axios.post('http://localhost:7777/fileUpload/QuestionBoard',formData,{
                        headers:{ 'Content-Type': 'multipart/form-data'}
                        })
                        .then(res=>{
                             console.log("등록 성공"+res.data)
                              alert('수정이 완료되었습니다!')
                               alert('등록 성공! - ' + res)
                                 this.$router.push({
                            name: 'K1QuestionView',
                           
                        })
                        }).catch(err=>{
                             console.log(err)
                             
                        })
                },1000)
         },
    },
      
    }
</script>