import {
    FETCH_BRAND_CHECK_BOARD_LIST,
    FETCH_BRAND_CHECK_BOARD,

    FETCH_QUESTION_BOARD_LIST,
    FETCH_QUESTION_BOARD,

    USER_LOGIN,
    FETCH_USER_INFO,
    FETCH_SESSION,
} from './mutation-types'

export default {
    [FETCH_BRAND_CHECK_BOARD_LIST] (state, brandCheckBoards) {
        state.BrandCheckBoards = brandCheckBoards
    },
    [FETCH_BRAND_CHECK_BOARD ] (state, brandCheckBoard) {
        state.BrandCheckBoard = brandCheckBoard
    },

    [FETCH_QUESTION_BOARD_LIST] (state, QuestionBoards) {
      state.QuestionBoards = QuestionBoards
   },
    [FETCH_QUESTION_BOARD ] (state, QuestionBoard) {
      state.QuestionBoard = QuestionBoard
    },

    [USER_LOGIN] (state, isLogin) {
        state.isLogin = isLogin
        console.log('isLogin: ' + isLogin)
      },
      [FETCH_USER_INFO] (state, userInfo) {
        state.userInfo = userInfo
        console.log('userInfo' + userInfo)
      },
      [FETCH_SESSION] (state, session) {
        state.session = session
      },
}
