/*
    里面包含所有action creator
    同步的action都返回一个对象
    异步的action都返回一个函数！（必须引入redux-thunk插件）
 */
import {INCREMENT, DECREMENT} from "./action-types";

// 增加
export const increment = (data) => (
    {
        type: INCREMENT,
        data: data
    }
)

// 减少
export const decrement = (data) => (
    {
        type: DECREMENT,
        data: data
    }
)

// 异步
export const incrementAsync = (data) => (dispatch) => {
    // 异步代码
    setTimeout(() => {
        // 2s后才去返回一个action对象
        dispatch(increment(data))
    },2000)
}
