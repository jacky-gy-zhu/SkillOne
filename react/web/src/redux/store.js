import React from "react"
import {applyMiddleware, createStore} from "redux"
import thunk from "redux-thunk"

import reducers from './reducers'

// 生成一个store对象
const store = createStore(
    reducers,
    applyMiddleware(thunk)  // 应用上异步中间件
) // 内部会第一次调用reducer函数得到初始state

export default store