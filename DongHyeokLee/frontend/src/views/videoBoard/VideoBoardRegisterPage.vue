<template>
    <div align="center">
        <h2>게시물 작성</h2>
        <video-board-register-form @submit="onSubmit"/>
    </div>
</template>

<script>

import axios from 'axios'
import VideoBoardRegisterForm from '@/components/videoBoard/VideoBoardRegisterForm.vue'


export default {
    name: 'VideoBoardRegisterPage',
    components: {
        VideoBoardRegisterForm
    },
    methods: {
        onSubmit (payload) {
            const { title, content, writer, files } = payload
            let formData = new FormData()
            const fileName = files[0].name
            //파일 저장 된거 불러 오는 방법을 위치랑 이름으로 밖에 못하겠어가지고 일단 db에 이름값을 전달 하도록 함
            for (let idx = 0; idx < files.length; idx++) {
                formData.append('fileList', files[idx])
            }
           //엑시오스 두개 사용 이래도 되나?
            axios.all([
                 axios.post('http://localhost:7777/videoBoard/register', { title, writer, content, fileName}),
                 axios.post('http://localhost:7777/videoBoard/uploadImg', formData,{
                     headers: {
                         'Content-Type' : 'multipart/form-data'
                     }
                 })
            ])
                    .then(axios.spread(() => {
                            alert('등록 성공')

                        this.$router.push({
                            name: 'VideoBoardListPage'
                        })
                    
                    })
                )
                    .catch(() => {
                        alert('문제 발생!')
                    })
        }
    }
}

</script>