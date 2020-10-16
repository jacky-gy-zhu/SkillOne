import React, {Component} from 'react'
import {NavLink, Switch, Route, Redirect} from 'react-router-dom'

import MyNavLink from './my-nav-link'
import Profile from './profile/profile'
import Home from '../views/home'
import About from '../views/about'
import CommentViewer from '../views/comment-viewer'
import Counter from "../containers/counter";

export default class App extends Component {

    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-xs-offset-2 col-xs-8">
                        <div className="page-header"><h2>React Router Demo1111</h2></div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-xs-offset-2 col-xs-2">
                        <div className="list-group">
                            <MyNavLink className="list-group-item" to="/home">Home</MyNavLink>
                            <MyNavLink className="list-group-item" to="/about">About</MyNavLink>
                            <MyNavLink className="list-group-item" to="/comment">Comment</MyNavLink>
                            <MyNavLink className="list-group-item" to="/counter">Counter</MyNavLink>
                        </div>
                    </div>
                    <div className="col-xs-6">
                        <div className="panel">
                            <div className="panel-body">
                                <Switch>
                                    <Route path='/home' component={Home}/>
                                    <Route path='/about' component={About}/>
                                    <Route path='/comment' component={CommentViewer}/>
                                    <Route path='/profile' component={Profile}/>
                                    <Route path='/counter' component={Counter}/>
                                    <Redirect to="/profile"/>
                                </Switch>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}