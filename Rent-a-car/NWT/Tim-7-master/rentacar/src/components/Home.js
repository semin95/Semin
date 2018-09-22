import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {Jumbotron, Grid, Row, Col, Image, Button} from 'react-bootstrap';
import LoginForm from './LoginForm';

export default class Home extends Component{

    state = {
        user: {}
    };

    onSubmit = user => {
        this.setState({user});
        console.log(user);
    };

    render(){
        return (
            <Grid>
                <Jumbotron>
            <h2> Welcome Home </h2>
            </Jumbotron>
            <Link to="/about">
                <Button bsStyle="primary">About</Button>
            </Link> 
            </Grid>


            /*<div>
                <LoginForm onSubmit={user => this.onSubmit(user)}/>
            </div>
            </Grid>*/
        )
    }
}