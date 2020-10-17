import React from 'react'
import {render} from 'react-dom'
import {BrowserRouter, HashRouter} from 'react-router-dom'
import {Provider} from 'react-redux'

import store from './redux/store'
import App from './components/app'
import 'bootstrap/dist/css/bootstrap.min.css';

render(
    (
        <Provider store={store}>
            <HashRouter>
                <App/>
            </HashRouter>
        </Provider>
    ),
    document.getElementById('root')
)