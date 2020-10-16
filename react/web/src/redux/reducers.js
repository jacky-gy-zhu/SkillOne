import React from "react"
import {combineReducers} from "redux"

import * as TYPES from './action-types'

/*
    包含n个reducer函数的模块
 */
const counter = (state = 0, action) => {

    console.log('counter: ', state, action)

    switch (action.type) {
        case TYPES.INCREMENT:
            return state + action.data*1
        case TYPES.DECREMENT:
            return state - action.data*1
        default:
            return state
    }
}

const comment = (state = [], action) => {
    switch (action.type) {
        case TYPES.COMMENT_ADD:
            return [action.data, ...state]
        case TYPES.COMMENT_DELETE:
            return state.filter((item, index) => index != action.data)
        case TYPES.COMMENT_GET:
            return action.data
        default:
            return state
    }
}

export default combineReducers({
    counter1:counter,
    comment
})