import React, { Component } from 'react';
import PropTypes from 'prop-types'

export default class Container extends Component {

    static propTypes = {
        comments: PropTypes.array.isRequired,
        deleteComment: PropTypes.func.isRequired
    }

    render() {
        return (
            <div>Hello World!</div>
        )
    }
}