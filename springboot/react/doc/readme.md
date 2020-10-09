>https://github.com/zxfjd3g/170925_react_test/blob/master/README.md

# package.json里面更改配置后用npm下载
> 1.package.json不存在时
> 命令:npm init可自动创建package.json文件
> 2.package.json存在时
> 直接命令:npm install 或者 npm install –save-dev会自动将package.json中的模块安装到node-modules文件夹下

# React
> https://reactjs.org/
> https://reactjs.org/docs/cdn-links.html
> https://reactjs.org/docs/typechecking-with-proptypes.html
> https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi?hl=en
> 声明式、组件化、一次学习，随处编写
```html
<script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
<script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
<script src="https://unpkg.com/prop-types@15.6/prop-types.js"></script>
<script src="https://unpkg.com/@babel/standalone@7.11.6/babel.min.js"></script>
<script crossorigin="anonymous" src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.min.js"></script>
```
```html
<script crossorigin src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
<script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://unpkg.com/prop-types@15.6/prop-types.min.js"></script>
<script src="https://unpkg.com/@babel/standalone@7.11.6/babel.min.js"></script>
<script crossorigin="anonymous" src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.20.0/axios.min.js"></script>
```
## 高效
>* 虚拟DOM，不总是操作DOM
>* DOM Diff算法，最小页面重绘

## 虚拟DOM
> React提供了一些API来创建一种特别的一般js对象
>* var element = React.createElement('h1',{id:'myTitle'},'hello')
>* 上面创建的就是一个简单的虚拟DOM对象

## JSX (JavaScript XML)
> 用于创建react虚拟DOM对象
>* var ele = <h1>Hello JSX!</h1>
>* 注意1：它不是字符串，也不是HTML/XML标签
>* 注意2：它最终产生的就是一个js对象
> 标签名任意
> 属性名任意

## ReactDOM.render(virtualDOM, containerDOM)
> 将虚拟DOM元素渲染到页面中的真实容器DOM中显示

## 模块与组件和模块化与组件化的理解
> 模块是一个js文件 （具有特定功能的js文件；有数据，以及对数据的操作，即变量和函数，对外暴露函数）
>* 如果是单一函数，则暴露函数；如果是多个函数需要对外暴露，则暴露对象。
> 模块化是指项目的编码方式是否是模块编码方式
> 组件是一个局部界面功能的集合，包含了js、css、html、images
> 组件化是指做一个页面的时候是以组件的方式来实现的

## 组件标签首字母大写

## 组件三大属性
### 1. state
> state是组件对象最重要的属性，值是对象（可以包含多个数据）
> 组件被称为"状态机"，通过更新组件的state来更新对应的页面显示（重新渲染组件）
> 编码操作
>
> * 初始化状态：
```javascript
constructor(props){
    super(props)
    this.state = {
        stateProp1: value1,
        stateProp2: value2
    }
}
```
>* 读取某个状态值
```javascript
    this.state.statePropertyName
```
>* 更新状态 -> 组件界面更新
```javascript
this.setState({
    stateProp1: value1,
    stateProp2: value2
})

this.setState(state => ({count:state.count+1}), () => {
    console.log(this.state.count)
})
```
>* (难点)bind的使用
```javascript
constructor(props) {
    super(props)
    // 初始化状态
    this.state = {
        isLikeMe: false
    }

    // 将新增的方法中的this强制绑定为组件对象
    this.handleClick = this.handleClick.bind(this)
}
```
>* 只要组件有状态就不能用工厂方式创建组件，只能用类的方式

### 2. props
> 默认属性值
```javascript
Person.defaultProps = {
    name: 'Mary'
}
```
> 对props中的属性值进行类型限制和必要性限制
```javascript
// v15
Person.propTypes = {
    name: React.PropTypes.string.isRequired,
    age: React.PropTypes.number.isRequired
}
// v16+
Person.propTypes = {
    name: PropTypes.string.isRequired,
    age: PropTypes.number
}
```
```javascript
//...的作用
//1. 打包
function fn(...as) {} 
fn(1, 2, 3)
//2. 解包
const arr1 = [1, 2, 3] 
const arr2 = [6, ...arr1, 9]
```

### 3. refs
```javascript
class MyComponent extends React.Component {

    constructor(props) {
        super(props);
        this.showInput1 = this.showInput1.bind(this)
        this.showInput2 = this.showInput2.bind(this)
        this.handleBlur1 = this.handleBlur1.bind(this)
        this.handleBlur2 = this.handleBlur2.bind(this)
    }

    showInput1() {
        const input = this.refs.content1
        alert(input.value)
    }

    showInput2() {
        alert(this.input2.value)
    }

    handleBlur1() {
        alert(this.input3.value)
    }

    handleBlur2(event) {
        alert(event.target.value)
    }

    render() {
        return (
            <div>
                <input type="text" ref="content1"/>&nbsp;&nbsp;
                <button onClick={this.showInput1}>提示输入数据1</button>&nbsp;&nbsp;
                <input type="text" ref={input => this.input2 = input}/>&nbsp;&nbsp;
                <button onClick={this.showInput2}>提示输入数据2</button>&nbsp;&nbsp;
                <input type="text" placeholder="失去焦点提示内容1" ref={input => this.input3 = input} onBlur={this.handleBlur1}/>
                <input type="text" placeholder="失去焦点提示内容2" onBlur={this.handleBlur2}/>
            </div>
        )
    }
}
```

## 功能界面的组件化编程流出（无比重要）
> 拆分组件：拆分界面，抽取组件
> 实现静态组件：使用组件实现静态页面效果
> 实现动态组件
> * 动态显示初始化数据
> * 交互功能（从绑定事件监听开始）

## 包含表单的组件分类
> 受控组件：表单项输入数据能自动收集成状态
```html
<input type="password" value={this.state.pwd} onChange={this.handleChange}/>
```
> 非受控组件：需要时才手动读取表单输入框中的数据
```html
<input type="text" ref={input => this.nameInput = input}/>
```

## 组件的生命周期
> see readme.doc
> 移除组件：ReactDOM.unmountComponentAtNode(containerDom)
>
> * componentWillUnmount()：组件将要被移除回调
### 生命周期流程：
> 第一次初始化渲染显示：ReactDOM.render()
>* constructor()：创建对象初始化state
>* UNSAFE_componentWillMount()：将要插入回调
>* render()：用于插入虚拟DOM回调
>* componentDidMount()：已经插入回调
> 每次更新state：this.setState()
>* UNSAFE_componentWillUpdate()：将要更新回调
>* render()：更新（重新渲染）
>* componentDidUpdate()：已经更新回调
> 移除组件：ReactDOM.unmountComponentAtNode(containerDom)
>* componentWillUnmount()：组件将要被移除回调
![Imgur](https://i.imgur.com/v2HjuQ3.png)

### this.setState({}) 区域更新
> setState会去拿新的状态去比较老的状态，Diff算法只更新更新过的状态

# React APP
## package.json
### 标示
> name
> version
### 依赖
> dependencies
> devDependencies
### 运行/打包
> scripts
## 创建项目并启动
```bash
npm install -g create-react-app
create-react-app hello-react
cd hello-react
npm start
```
## 发布产品
> npm run build
> npm install -g serve
> serve -s build
### 下载prop-types 
> npm install --save prop-types
### 下载axios
> npm install --save axios
### 箭头函数帮助不能在构造器里定义bind(this)
```javascript
handleSubmit = () => {

}
```
### 给组件类添加属性
```javascript
import PropTypes from 'prop-types'
static propTypes = {
    addComment: PropTypes.func.isRequired
}
```
### 给组件对象添加属性
```javascript
state = {
    userName: '',
    content: ''
}
```

## React Ajax
### axios：轻量级
>a. 封装XmlHttpRequest对象的ajax
>b. promise风格 [.then()]
>c. 可以用在浏览器端和node服务器端
```javascript
// get
axios.get('/user', {
    params: {
        id: 123
    }
})
.then(response => {
    console.log(response)
})
.catch(error => {
    console.log(error)
})

// post
axios.post('/user', {
    firstName: 'Fred',
    lastName: 'Flints'
})
.then(response => {
    console.log(response)
})
.catch(error => {
    console.log(error)
})
```
### fetch：原生函数，但老版本浏览器不支持
>a. 不再使用XmlHttpRequest对象提交ajax请求
>b. 为了兼容低版本但浏览器，可以引入兼容库fetch.js
```javascript
// get
fetch(url)
.then(response => {
    return response.json()
})
.then(data => {
    console.log(data)
}).catch(error => {
    console.log(error)
})
```
### js对象相互转换
```javascript
const users = result.items.map((item, index) => {
    return {
        name: item.login,
        url: item.html_url,
        avatarUrl: item.avatar_url
    }
})
const users = result.items.map((item, index) => (
    {
        name: item.login,
        url: item.html_url,
        avatarUrl: item.avatar_url
    }
))
```
### 组件间通讯方式
>* 方式一：通过props传递
>1）共同的数据放在父组件上，特有的数据放在自己的组件内部（state）
>2）通过props可以传递一般数据和函数数据，只能一层一层传递
>3）一般数据->父组件传递数据给子组件->子组件读取数据
>4）函数数据->子组件传递数据给父组件->子组件调用函数
>* 方式二：使用消息订阅（subscribe）-发布（publish）机制
>1）工具库：PubSubJS
>2）下载：npm install pubsub-js --save
>3）使用：
```javascript
//引入
import PubSub from 'pubsub-js' 
// 订阅消息（search）
PubSub.subscribe('search', (msg, searchName) => { 回调函数... }
// 发布消息（search）【触发事件的地方】
PubSub.publish('search', name)
```
>* 方式三：redux

# React-router相关API
## 组件
> <BrowserRouter/>
> <HashRouter/>
> <Route></Route>
> <Rediredt></Rediredt>
> <Link/>
> <NavLink></NavLink>
> <Switch></Switch>
## 其他
> history对象
> match对象
> withRouter函数
## 安装
> npm install --save react-router-dom
```
// index.js
import React from 'react'
import {render} from 'react-dom'
import {BrowserRouter} from "react-router-dom";

import App from './components/app'

render(
    (
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    ),
    document.getElementById('root')
)
```
```
// app.jsx
import {NavLink, Switch, Route, Redirect} from 'react-router-dom'

<NavLink className="list-group-item" to="/comment">Comment</NavLink>
<NavLink className="list-group-item" to="/profile2">Profile2</NavLink>
<Switch>
    <Route path='/comment' component={Comment}/>
    <Route path='/profile' component={Profile}/>
    <Route path='/profile2' component={Profile2}/>
    <Redirect to="/profile"/>
</Switch>
```
### MyNavLink 对NavLink的优化
```javascript
import React, {Component} from 'react'
import PropTypes from 'prop-types'
import {NavLink} from "react-router-dom"

export default class MyNavLink extends Component {

    static propTypes = {
        to: PropTypes.string.isRequired
    }

    render() {
        return <NavLink activeClassName="activeClass" {...this.props}></NavLink>
    }
}
```
### 如何编写路由效果
> 1）编写路由组件
> 2）在父路由组件中指定
>   路由链接：<NavLink/>
>   路由：<Switch><Route/></Switch>
###路径中设置动态参数（:xxx 占位符，即props.match.params.xxx变量名
```html
<Route path="/home/message/:id" component={MessageDetail}/>
```
```javascript
const {id} = props.match.params
```
### 路由请求和非路由请求
> 路由请求不会发送http请求：<NavLink to="/user">xxx</NavLink>
> 非路由请求会发送http请求：<a href="/user">xxx</a>
### javascript方法来发送路由请求
```javascript
// 历史记录是一个栈结构
// 路由请求push方式
this.props.history.push(`/home/message/${id}`)
// 路由请求replace方式
this.props.history.replace(`/home/message/${id}`)
// 路由请求回退
this.props.history.goBack()
// 路由请求前进
this.props.history.goForward()
```

# React-ui
> https://material-ui.com/
> https://thefront-styleguide.maccarianagency.com/ [！！！用户后台设计参考！！！]

## js、css文件按需按打包
### 安装react-app-rewired和babel-plugin-import
> npm install react-app-rewired --save-dev
> npm install babel-plugin-import --save-dev
### 修改默认配置
```json
"scripts": {
  "start": "react-app-rewired start",
  "build": "react-app-rewired build",
  "test": "react-app-rewired test --env=jsdom"
}
```
### 添加config-overrides.js
> 不需要再去引入css
```javascript
const {injectBabelPlugin} = require('react-app-rewired');
module.exports = function override(config, env) {
    config = injectBabelPlugin(['import', {libraryName: 'antd-mobile', style: 'css'}], config);
    return config;
}
```

# Redux
## 下载依赖包
> npm install --save redux
## redux核心API
### createStore()
> 作用：创建包含指定reducer的store对象
```javascript
import {createStore} from 'redux'
import counter from './reducers/counter'
const store = createStore(counter)
```
### store对象
![Imgur](https://i.imgur.com/OHBGnpT.png)
> 作用：redux库最核心的管理对象
> 它的内部维护者
> * state
> * reducer
> 核心方法：
> * getState()
> * dispatch(action)
> * subscribe(listener)
> 编码
```javascript
store.getState()
store.dispatch({type:'INCREMENT', number})
store.subscribe(render)
```
### Redux的三个核心概念
1. action
    标示要执行行为的对象
    包含2个方面的属性
    type: 标示属性，值为字符串，唯一，必要属性
    xxx: 数据属性，值类型任意，可选属性
    例子：
```javascript
    const action = {
        type: 'INCREMENT',
        data: 2
    }
```
    Action Creator（创建Action的函数）
    const increment = (number) => ({type: 'INCREMENT', data: number})
2. reducer
    根据老的state和action，产生新的state的纯函数
    样例
```javascript
    export default function counter(state = 0, action) {
        switch(action.type) {
            case 'INCREMENT':
                return state + action.data
            case 'DECREMENT':
                return state - action.data
            default:
                return state
        }
    }
```
    注意：
    返回一个新的状态
    不要修改原来的状态
3. store对象
    将state、action与reducer联系在一起的对象
    如何得到此对象？
```javascript
    import {createStore} from 'redux'
    import reducer from './reducers'
    const store = createStore(reducer)
```
    此对象的功能？
    getState()：得到state
    dispatch(action)：分发action，触发reducer调用，产生新的state
    subscribe(listener)：注册监听，当产生了新的state时，自动调用
### import引入方式
> import * as actions from '../redux/actions' // 在没有export default的方式下
> import {INCREMENT, DECREMENT} from '../redux/actions'

# React-redux
> 一个react的插件库
> 专门用来简化react应用中使用redux
> npm install --save react-redux
```javascript
import React from 'react'
import ReactDOM from 'react-dom'
import {Provider} from 'react-redux'

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>
, document.getElementById('root')
)
```
```javascript
import React, {Component} from 'react'
import {connect} from 'react-redux'
import PropTypes from 'prop-types'

class Counter extends Component {
    static propTypes = {
        count: PropTypes.number.isRequired,
        increment: PropTypes.func.isRequired,
        decrement: PropTypes.func.isRequired
    }
}
export default connect(
    state => ({
        count: state
    }), // 传递reducer里面的state，state的结构在reducer里面定义，包括初始化结构
    {increment, decrement} // 传递reducer里面的action
)(Counter) // reducer是和store绑定的，而store在<Provider store={store}>标签里面包含
```
## React-redux将所有组件分成两大类
1）UI组件
    只负责UI的呈现，不带有任何业务逻辑
    通过props接受数据（一般数据和函数）
    不使用任何Redux的API
    一般保存在components文件夹下
2）容器组件
    负责管理数据和业务逻辑，不负责UI的呈现
    使用Redux的API
    一般保存在containers文件夹下
> redux整合多个reducer
```javascript
// 在reducers.js里面
import {combineReducers} from 'redux'
const counter = (state = 0, action) => {
    return state
}
const comment = (state = [], action) => {
    return state
}
export default combineReducers({
    counter,
    comment
})

// 在containers里面需要修改
export default connect(
    state => ({
        count: state.counter // 传递reducer里面的state这个对象里面的对应的state
    }), 
    {increment, decrement, incrementAsync}
)(Counter)
```

## redux异步编程
> 下载redux插件（异步中间件）
> npm install --save redux-thunk
```javascript
import {createStore, applyMiddleware} from 'redux'
import thunk from 'redux-thunk'
// 根据counter函数（reducer）创建store对象
const store = createStore(
    counter,
    applyMiddleware(thunk) // 应用上异步中间件
)
export default store
```
actions.js里面包含所有action creator
同步的action都返回一个对象
异步的action都返回一个函数！
```javascript
// 异步
export const incrementAsync = (data) => (dispatch) => {
    // 异步代码
    setTimeout(() => {
        // 2s后才去返回一个action对象
        dispatch(increment(data))
    },2000)
}
```
### some ES6 array operation
```javascript
// 删除
comments.splice(index, 1)
newComments = comments.filter((comment, index) => index != action.data)
// 添加
comments.splice(index, 0, {})
newComments = [{}, ...comments]
// 替换（改）
comments.splice(index, 1, {})
```

# 跨域访问
```javascript
getData(){
        //修改请求头
        let myHeaders = new Headers({
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'text/plain'
        });
        fetch('http://127.0.0.1:8080/hello',{
            method:'GET',
            headers: myHeaders,
            mode: 'cors',
            //转或称字符串格式
        }).then(res=>res.json()).then(
            data=>{
                console.log(data);
                // eslint-disable-next-line
                data.map((key)=>{
                    this.setState({
                        mytext: [...this.state.mytext, key]
                    })
                })
            }
        )
}
```
## withRouter高阶组件
```javascript
import {withRouter} from 'react-router-dom'
/*
withRouter高阶组件:
包装非路由组件, 返回一个新的组件
新的组件向非路由组件传递3个属性: history/location/match
 */
export default withRouter(LeftNav)
```
## setState默认是异步的方法
    是一个异步方法
    setState(object, () => {
      在状态更新且重新render()后执行    
    })  

## rich text editor
> https://github.com/jpuri/react-draft-wysiwyg
```javascript
import {EditorState, convertToRaw} from 'draft-js'
import {Editor} from 'react-draft-wysiwyg'
import draftToHtml from 'draft-js-to-html'
import htmlToDraft from 'html-to-draftjs'
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css'

render(){
    return <Editor
                editorState={editorState}
                wrapperClassName="demo-wrapper"
                editClassName="demo-editor"
                onEditorStateChange={this.onEditorStateChange}
            />
            /*
            <textarea
                disabled
                value={draftToHtml{convertToRaw(editorState.getCurrentContent()))}
            />
             */
}

...
resolve({data:{link:url}})
```