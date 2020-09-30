import {createStore} from "redux"

import * as reducers from './reducers'

export default createStore(reducers.counter)