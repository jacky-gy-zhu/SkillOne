/*
    包含n个reducer函数的模块
 */
import {INCREMENT, DECREMENT} from './action-types'

export const counter = (state = 0, action) => {

    console.log('counter: ', state, action)

    switch (action.type) {
        case INCREMENT:
            return state + action.data*1
        case DECREMENT:
            return state - action.data*1
        default:
            return state
    }
}