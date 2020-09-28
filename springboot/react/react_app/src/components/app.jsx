import React, {Component} from 'react'
import {NavLink, Switch, Route, Redirect} from 'react-router-dom'
import Comment from './comment/app/app'
import Profile from './profile/profile'
import Profile2 from './profile2/profile'

export default class App extends Component {

    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-xs-offset-2 col-xs-8">
                        <div className="page-header"><h2>React Router Demo</h2></div>
                    </div>
                </div>
                <div className="row">
                    <div className="col-xs-offset-2 col-xs-2">
                        <div className="list-group">
                            <NavLink className="list-group-item" to="/comment">Comment</NavLink>
                            <NavLink className="list-group-item" to="/profile2">Profile2</NavLink>
                        </div>
                    </div>
                    <div className="col-xs-offset-2 col-xs-6">
                        <div className="panel">
                            <div className="panel-body">
                                <Switch>
                                    <Route path='/comment' component={Comment}/>
                                    <Route path='/profile' component={Profile}/>
                                    <Route path='/profile2' component={Profile2}/>
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