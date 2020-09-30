import React from 'react'
import {render} from 'react-dom'
import {BrowserRouter} from 'react-router-dom'
import {createStore} from 'redux'
import {Provider} from 'react-redux'

import App from './components/app'
import {counter} from './redux/reducers'
import store from './redux/store'

// 生成一个store对象
// const store = createStore(counter) // 内部会第一次调用reducer函数得到初始state
console.log('store', store)

render(
    (
        <Provider store={store}>
            <BrowserRouter>
                <App/>
            </BrowserRouter>
        </Provider>
    ),
    document.getElementById('root')
)