# REACT_SpringBoot3
DataHolder, Save, Edit, Add, Details

## Упатство за стартување 
Упатство за стартување на back-end:
Отворете го проектот во IntelliJ IDEA
Импортирајте ги зависностите од pom.xml (Import changes)
За да ја извршите апликацијата отворете
src -> main -> java -> elena.rtoska.trail_races_project -> TrailRacesProjectApplication
Десен клик на TrailRacesProjectApplication -> Run "TrailRacesProjectApplication"
Back-end се стартува на http://localhost:8080/

Упатство за стартување на front-end:
Откако ја извршивте апликацијата, отворете терминал:
View -> Tool Windows -> Terminal или alt+F12
Преку терминал влезете во папката frontend со командата:
cd frontend
Инсталирајте ги потребните зависности со командата:
npm install
Откако ќе се инсталираат потребните зависности, стартувајте ја апликацијата со командата:
npm start
Front-end апликацијата е стартувана на http://localhost:3000/

Упатство за стартување на базата:
-Базата се стартува на http://localhost:8080/h2 (поставките се во application.properties)



## Спецификација за третата лабораториска вежба
Во оваа вежба треба да се искористи целосната функционалност од лаб. 2.
претходно направете промени во @RestController-ите со овозможување на CORS повици од следниот домен: http://localhost:3000.
Креирајте нов ReactJS проект со командата npx create-react-app lab3
Во package.json додадете ги следните зависности за да може да користете bootstrap, аxios (http клиент), react-router и react-router-dom (за рутирање во апликацијата) и слично:
"dependencies": {
    "axios": "^0.19.0",
    "bootstrap": "^4.4.1",
    "jquery": "^3.4.1",
    "popper.js": "^1.16.0",
    "react": "^16.12.0",
    "react-router": "^5.1.2",
    "react-router-dom": "^5.1.2",
    "react-dom": "^16.12.0",
    "react-scripts": "0.9.5",
    "font-awesome": "^4.7.0",
    "moment": "^2.24.0",
    "react-moment": "^0.9.6"
  }
Навигирајте до проектот и извршете ја командата npm install за да ги инсталирате горенаведените библиотеки.

Импортирајте ги библиотеките во index.js за да може да ги користете:

   import 'bootstrap/dist/css/bootstrap.min.css';
   import 'font-awesome/css/font-awesome.css';
   import 'jquery/dist/jquery';
   import 'bootstrap/dist/js/bootstrap.min';
Слично како кај аудиториските вежби, креирајте header кој има три табови:
- Home (при клик не носи на иницијалната патека : /) - Pizzas (при клик не носи на патека : /pizzas) - Ingredients (при клик не носи на патека /ingredients) За овие функционалности треба да искористите рутирање (react-router-dom). Доколку некој проба да пристапи некоја непостоечка патека, се пренасочува на почетна страна (Redirect).

Пример за изглед на header-от:

 <header>
            <nav className="navbar navbar-expand-md navbar-dark navbar-fixed bg-dark">
                <a className="navbar-brand">Home</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse"
                        aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
                <div className="collapse navbar-collapse" id="navbarCollapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <a className="nav-link">Pizzas</a>
                        </li>
                        <li className="nav-item ">
                            <a className="nav-link">Ingredients</a>
                        </li>
                    </ul>
                    <form className="form-inline mt-2 mt-md-0 ml-3">
                        <a className="btn btn-outline-info my-2 my-sm-0" href="#">Login</a>
                    </form>
                </div>
            </nav>
        </header>

При клик на табот за ingredients треба да се повика соодветната компонента каде што ќе се прати барање барање за да се земат сите досега креирани ingredients и ќе се прикажат во табела (доколку се уште нема ingredients, наместо празна табела да се прикаже порака дека моментално нема ingredients). Во табелата покрај сите атрибути на состојките, за секоја состојка треба да има копчиња:
Edit - кое не носи на патеката „/ingredients/{ingredientId}/edit“ каде што ќе ни се појави форма каде може да ја едитираме селектираната состојка (полињата ќе бидат пополнети со веќе постоечките вредности на состојката).
Remove - кое ја брише дадената состојка.
Details - не носи на патеката „/ingredients/{ingredientId}/details“ каде што се прикажува view по ваш избор каде освен атрибутите на состојката, дополнително се прикажуваат и сите пици во кои се содржи таа состојка (соодветен повик до сервер).
Под табелата треба да има копче „Add new ingredient“ кое не носи на „/ingredients/new“ каде што ќе ни се појави празна форма за да додадеме нова состојка.

Пример html за табелата и копчињата:

    <div className="row">
            <h4 className="text-upper text-left">Ingredients</h4>
            <div className="table-responsive">
                <table className="table tr-history table-striped small">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Amount</th>
                        <th scope="col">Spicy</th>
                        <th scope="col">Veggie</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td scope="col">Ham</td>
                        <td scope="col">200g</td>
                        <td scope="col">False</td>
                        <td scope="col">False</td>
                        <td scope="col">
                            <button className="btn btn-sm btn-secondary">
                                <span className="fa fa-edit"/>
                                <span><strong>Edit</strong></span>
                            </button>
                            <button className="btn btn-sm btn-outline-secondary ">
                                <span className="fa fa-remove"/>
                                <span><strong>Remove</strong></span>
                            </button>
        <button className="btn btn-sm btn-outline-dark">
     <span><strong>Details</strong></span>
</button>
                        </td>
                    </tr>
                    <tr>
                        <td scope="col">Mushroom</td>
                        <td scope="col">100g</td>
                        <td scope="col">False</td>
                        <td scope="col">True</td>
                        <td scope="col">
                            <button className="btn btn-sm btn-secondary">
                                <span className="fa fa-edit"/>
                                <span><strong>Edit</strong></span>
                            </button>
                            <button className="btn btn-sm btn-outline-secondary ">
                                <span className="fa fa-remove"/>
                                <span><strong>Remove</strong></span>
                            </button>
<button className="btn btn-sm btn-outline-dark">
     <span><strong>Details</strong></span>
</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <button className="btn btn-outline-secondary">
                <span><strong>Add new ingredient</strong></span>
            </button>
        </div>
Пример html за формата за додавање/едитирање на нова состојка.

   <div className="row">
            <form className="card">
                <h4 className="text-upper text-left">Add/Edit Ingredient</h4>
                <div className="form-group row">
                    <label htmlFor="ingredient" className="col-sm-4 offset-sm-1 text-left">Ingredient name</label>
                    <div className="col-sm-6">
                        <input type="text" className="form-control" id="ingredient" placeholder="Ingredient name"/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="amount" className="col-sm-4 offset-sm-1 text-left">Amount</label>
                    <div className="col-sm-6">
                        <input type="text" className="form-control" id="amount" placeholder="Amount"/>
                    </div>
                </div>
                <div className="form-group row">
                    <label htmlFor="veggie" className="col-sm-4 offset-sm-1 text-left">Veggie</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox" className="form-control" id="veggie"/>
                    </div>
                </div>

                <div className="form-group row">
                    <label htmlFor="spicy" className="col-sm-4 offset-sm-1 text-left">Spicy</label>
                    <div className="col-sm-6 col-xl-4">
                        <input type="checkbox" className="form-control" id="spicy"/>
                    </div>
                </div>

                <div className="form-group row">
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            type="submit"
                            className="btn btn-primary text-upper">
                            Save
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-warning text-upper">
                            Reset
                        </button>
                    </div>
                    <div
                        className="offset-sm-1 col-sm-3  text-center">
                        <button
                            className="btn btn-danger text-upper">
                            Cancel
                        </button>
                    </div>
                </div>
            </form>
        </div>
Имплементирајте ги следните барања:
При додавање/едитирање на состојка, да се направи валидација, така што полињата за име и количина не смеат да бидат празни и подолги од 50 карактери. Доколку условите за валидација не се исполнети, копчето Save треба да биде disabled.
Доколку зачувувањето на состојката е успешно, корисникот се враќа на патеката /ingredients.
