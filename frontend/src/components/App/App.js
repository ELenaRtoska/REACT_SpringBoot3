import React, {Component} from 'react';
import './App.css';
import Header from "../Header/header";
import {BrowserRouter as Router, Switch, Route, Redirect} from "react-router-dom";
import Pizzas from "../Pizzas/pizzas";
import Ingredients from "../Ingredients/ingredients";
import HomePage from "../HomePage/homePage";
import Error from "../Error/error";

class App extends Component{

    constructor(props) {
        super(props);

        this.state = {
            error: {}
        };

    }

    render() {
          return (
              <Router>
                <Header/>
                <Switch>
                    <Route path={"/pizzas"}>
                        <Pizzas handleError={this.handleError}/>
                    </Route>
                    <Route path={"/ingredients"} render={(props) => <Ingredients {...props} handleError={this.handleError}/>}>
                    </Route>
                    <Route path={"/error"}>
                        <Error error={this.state.error}/>
                    </Route>
                    <Route path={"/"}>
                        <HomePage handleError={this.handleError}/>
                    </Route>
                    <Redirect to={"/"}/>
                </Switch>
              </Router>
          );
    }

    handleError = (error, history) => {
        this.setState({
            error: error
        }, () => {
            history.push("/error");
        });
    };

}

export default App;
