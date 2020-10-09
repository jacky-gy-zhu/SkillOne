/*
    里面包含所有action creator
    同步的action都返回一个对象
    异步的action都返回一个函数！（必须引入redux-thunk插件）
 */
import * as TYPES from "./action-types";

/*
    ######## Actions for counter ########
 */
// 增加
export const increment = (data) => ({type: TYPES.INCREMENT, data: data})

// 减少
export const decrement = (data) => ({type: TYPES.DECREMENT, data: data})

// 异步
export const incrementAsync = (data) => (dispatch) => {
    // 异步代码
    setTimeout(() => {
        // 2s后才去返回一个action对象
        dispatch(increment(data))
    },2000)
}

/*
    ######## Actions for comment ########
 */
// add comment
export const addComment = (data) => ({type: TYPES.COMMENT_ADD, data: data})

// delete comment
export const deleteComment = (data) => ({type: TYPES.COMMENT_DELETE, data: data})

// get comments
export const getComment = () => (dispatch) => {
    // 获取评论
    const comments = [
        {
            userName: 'Tom',
            content: 'React挺好的！'
        },
        {
            userName: 'Jack1',
            content: 'React太好了！'
        }
    ]
    setTimeout(() => {
        dispatch(loadComments(comments))
    },2000)
}
const loadComments = (data) => ({type: TYPES.COMMENT_GET, data: data})
