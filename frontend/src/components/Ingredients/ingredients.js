import React, {Component} from "react";
import IngredientsService from "../../service/ingredientsAxios";
import Table from "../Table/table";
import {Switch, Route, Link, Redirect} from "react-router-dom";
import EditIngredient from "./EditIngredient/editIngredient";
import DetailsIngredient from "./DetailsIngredient/detailsIngredient";
import AddIngredient from "./AddIngredient/addIngredient";
import ShowIngredients from "./ShowIngredients/showIngredients";

class Ingredients extends Component{

    constructor(props) {
        super(props);

        this.state = {
            ingredients: []
        };

    }

    render() {
        return (
            <div className={"container"}>
                <Switch>
                    <Route path={`${this.props.match.path}`} exact>
                        <ShowIngredients ingredients={this.state.ingredients}
                                         deleteAction={this.deleteIngredient}
                                         {...this.props}/>
                    </Route>
                    <Route path={`${this.props.match.path}/:id/edit`} exact>
                        <EditIngredient updateIngredient={this.updateIngredient}/>
                    </Route>
                    <Route path={`${this.props.match.path}/:id/details`} exact>
                        <DetailsIngredient/>
                    </Route>
                    <Route path={`${this.props.match.path}/new`} exact>
                        <AddIngredient addIngredients={this.addIngredient}/>
                    </Route>
                    <Redirect to={"/"}/>
                </Switch>
            </div>
        );
    }

    componentDidMount() {
        IngredientsService.getAllIngredients()
            .then((response) => {
                let data = response.data;
                this.setState({
                    ingredients: data
                });
            });
    }
    
    updateIngredient = (ingredient) => {
        IngredientsService.updateIngredient(ingredient)
            .then((response) => {
                let data = response.data;
                this.setState(oldState => {
                    let newIngredients = oldState.ingredients.map((item) => {
                        if(item.id === ingredient.id)
                            return data;
                        return item;
                    });
                    return {
                        ingredients: newIngredients
                    }
                }, () => this.props.history.push("/ingredients"));
            })
            .catch((error) => {
                this.props.handleError(error, this.props.history);
            });
    };

    deleteIngredient = (id) => {
        IngredientsService.deleteIngredient(id)
            .then(() => {
                this.setState((oldState) => {
                    let newIngredients = oldState.ingredients.filter((item) => {
                        return item.id !== id;
                    });
                    return {
                        ingredients: newIngredients
                    }
                });
            })
            .catch((error) => {
                this.props.handleError(error, this.props.history);
            });
    };

    addIngredient = (ingredient) => {
        IngredientsService.addIngredient(ingredient)
            .then((response) => {
                debugger;
                let item = response.data;
                this.setState((oldState) => {
                    return {
                        ingredients: [...oldState.ingredients, item]
                    }
                }, () => this.props.history.push("/ingredients"));
            })
            .catch((error) => {
                this.props.handleError(error, this.props.history);
            });
    };

}

export default Ingredients;