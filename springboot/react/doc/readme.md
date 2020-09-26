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
```
```html
<script crossorigin src="https://unpkg.com/react@16/umd/react.production.min.js"></script>
<script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.production.min.js"></script>
<script src="https://unpkg.com/prop-types@15.6/prop-types.min.js"></script>
<script src="https://unpkg.com/@babel/standalone@7.11.6/babel.min.js"></script>
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
>
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