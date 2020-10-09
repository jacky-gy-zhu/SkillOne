import React from 'react'
import {render} from 'react-dom'
import {BrowserRouter} from 'react-router-dom'
import {Provider} from 'react-redux'

import store from './redux/store'
import App from './components/app'

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