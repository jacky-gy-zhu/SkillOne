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