<template>
    <v-container style="width: 700px; margin-top: 85px; font-size: 14px">
      <v-flex>
            <v-card style="height: 320px" v-if="!boards || (Array.isArray(boards) && boards.length === 0)">
                    현재 등록된 게시물이 없습니다!
            </v-card>
            <v-card style="margin-bottom: 30px;" v-else v-for="board in boards" :key="board.boardNo">
                <form @submit.prevent="onSubmit(board.boardNo)">
                    <table style="width: 100%;">
                        <tr>
                            <td style="padding: 14px 0px 14px 16px; font-weight: bold;">
                                {{ board.writer }}
                            </td>
                            <td align="right" style="padding-right: 12px;"> 
                                <v-menu offset-y min-width="100">
                                    <template v-slot:activator="{ on }">
                                        <v-btn icon v-on="on">
                                            <v-icon>
                                                mdi-dots-horizontal
                                            </v-icon>
                                        </v-btn> 
                                    </template>
                                    <v-list>
                                        <v-list-item-title> 
                                            <v-btn @click="onDelete(board.boardNo)">삭제</v-btn>
                                        </v-list-item-title>
                                        <v-list-item-title> 
                                            <router-link :to="{ name: 'BoardModifyPage', params: { boardNo: board.boardNo } }">
                                                게시물 수정
                                            </router-link>
                                        </v-list-item-title>
                                    </v-list>
                                </v-menu>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2">
                                <v-img width="672px" :src="require(`@/assets/mImage/${board.boardImage}`)"/>
                            </td>
                        </tr >
                        <!-- <tr align="left"  >
                            <div v-for="likes in loginLikes" :key="likes.likedNo"  >
                            <td style="padding: 6px 9px" colspan="2" v-if="likes.boardCheck==board.boardNo" >
                                <v-btn icon @click="onLikes(board.boardNo)" >
                                    <v-icon  color="black">
                                        mdi-cards-heart
                                    </v-icon>
                                </v-btn>
                                <v-btn icon @click="onLikes(board.boardNo)">
                                    <v-icon color="black">
                                        mdi-chat-outline
                                    </v-icon>
                                </v-btn>
                            </td>
                            </div>
                            <div > 
                            <td style="padding: 6px 9px" colspan="2">
                                <v-btn icon @click="onLikes(board.boardNo)" >
                                    <v-icon  color="black">
                                        mdi-cards-heart-outline
                                    </v-icon>
                                </v-btn>
                                <v-btn icon @click="onLikes(board.boardNo)">
                                    <v-icon color="black">
                                        mdi-chat-outline
                                    </v-icon>
                                </v-btn>
                            </td>
                            </div>
                        </tr> -->
                        <tr align="left"  >
                            <div v-for="likes in loginLikes" :key="likes.likedNo"  >
                            <td style="padding: 6px 9px" colspan="2" v-if="likes.boardCheck==board.boardNo" >
                                <v-btn icon @click="onLikes(board.boardNo)" >
                                    <v-icon  color="black">
                                        mdi-cards-heart
                                    </v-icon>
                                </v-btn>
                                <v-btn icon @click="onLikes(board.boardNo)">
                                    <v-icon color="black">
                                        mdi-chat-outline
                                    </v-icon>
                                </v-btn>
                            </td>
                            </div>
                            <div>
                            <!-- <div v-for="likes in loginLikes" :key="likes.likedNo"  > -->
                            <!-- <td style="padding: 6px 9px" colspan="2" v-if="likes.boardCheck!=board.boardNo">-->
                            <td style="padding: 6px 9px" colspan="2">
                                <v-btn icon @click="onLikes(board.boardNo)" >
                                    <v-icon  color="black">
                                        mdi-cards-heart-outline
                                    </v-icon>
                                </v-btn>
                                <v-btn icon @click="onLikes(board.boardNo)">
                                    <v-icon color="black">
                                        mdi-chat-outline
                                    </v-icon>
                                </v-btn>
                            </td>
                            </div>
                        </tr>
                        <!-- <tr align="left"  >
                            <td style="padding: 6px 9px" colspan="2">
                                <v-btn icon @click="onLikes(board.boardNo)" >
                                    <v-icon  color="black">
                                        mdi-cards-heart-outline
                                    </v-icon>
                                </v-btn>
                                <v-btn icon @click="onLikes(board.boardNo)">
                                    <v-icon color="black">
                                        mdi-chat-outline
                                    </v-icon>
                                </v-btn>
                            </td>
                        </tr> -->
                        <tr v-if="board.likes.length" align="left">
                            <td colspan="2" style="padding: 0px 0px 8px 16px">
                                <div id="likesCnt">{{ board.likes.length }}명이 좋아합니다</div>
                            </td>
                        </tr>
                        <tr align="left">
                            <td colspan="2" style="padding-left: 16px">
                                <div style="display: flex;">
                                    <div style="font-weight: bold;" >
                                        {{ board.writer }}&nbsp;
                                    </div>
                                    {{ board.content }}
                                </div>
                            </td>
                        </tr>
                        <tr v-if="board.comments.length"> 
                            <td style="padding-left: 16px;">
                                <router-link style="text-decoration: none; color: grey" :to="{
                                    name: 'BoardReadPage',
                                    params: {boardNo: board.boardNo.toString()}}">
                                    댓글 {{ board.comments.length}}개 모두 보기
                                </router-link>
                            </td>
                        </tr>
                        <tr>
                            <!-- <comment-list :boardNo="board.boardNo"/> -->
                            <!-- <td>
                                <div v-for="comment in twoComments" :key="comment.commentNo">
                                    <span style="font-weight: bold;">{{ comment.writer }}&nbsp;</span>
                                    {{ comment.content }}
                                </div>
                            </td> -->
                        </tr>
                        <tr align="left" style="font-size: 10px">
                            <td colspan="2" style="padding: 8px 0px 16px 16px; color: grey">
                                {{ board.regDate }}
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <hr style="border: 0; height: 1px; background: #d8d8d8; "/>
                            </td>
                        </tr>
                        <tr align="left">
                            <td style="padding: 14px 0px 14px 16px;">
                                <textarea type="text" style="width: 100%; height: 18px"  placeholder="댓글 달기..." v-model="content"/>
                            </td>
                            <td align="right"> 
                                <v-btn text color="primary" type="submit">
                                    게시
                                </v-btn>
                            </td>
                        </tr>
                        
                    </table>
                </form>
            </v-card>
        </v-flex>
    </v-container>
</template>

<script>
import { mapState, mapActions } from 'vuex'
import axios from 'axios'
export default {
    name: 'BoardList',
    props: {
        boards: {
            type: Array
        },
        // loginLikes: {
        //     type: Array
        // }
    },
    data() {
        return {
            loginInfo: JSON.parse(localStorage.getItem('loginInfo')),
            content: '',
        }
    },
    computed: {
        ...mapState(['loginLikes']),
    },
    mounted () {
        this.fetchLoginLikes(this.loginInfo.memberNo)
    },
    methods: {
        ...mapActions(['fetchLoginLikes']),
        onDelete(boardNo) {
            console.log(boardNo)
            this.$emit('click', {boardNo})
        },
        onSubmit(boardNo) {
            const { content } = this
            this.$emit('submit', { boardNo, content })
        },
        // onLikes(boardNo) {
        //     this.$emit('click', {boardNo, memberNo: this.loginInfo.memberNo})
        // },
        onLikes(boardNo) {
            axios.post(`http://localhost:7777/likes/${boardNo}/${this.loginInfo.memberNo}`, {boardNo, boardCheck: boardNo, memberNo: this.loginInfo.memberNo})
                .then(() => {
                    history.go(0);
                })
                .catch(() => {
                    alert('문제 발생!')
                })
        },
    }
}
</script>
