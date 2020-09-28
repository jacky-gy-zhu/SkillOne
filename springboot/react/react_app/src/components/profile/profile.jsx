import React, {Component} from 'react'

import Search from "./search";
import Main from "./main";

export default class Profile extends Component {

    state = {
        searchName: ''
    }

    setSearchName = (searchName) => {
        this.setState({searchName})
    }

    render() {
        return (
            <div id="profile">
                <div className="container">
                    <Search setSearchName={this.setSearchName}/>
                    <Main searchName={this.state.searchName}/>
                </div>
            </div>
        )
    }
}