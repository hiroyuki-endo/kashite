import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { useSelector } from "react-redux";
import './App.css';
import "antd/dist/antd.css";
import Auth from './Auth'
import Main from './Main'
import Top from './Top'
import errorNotification from './components/ErrorNotification'

function App() {
  const errors = useSelector(state => state.errors);
  const error = errors.slice(-1)[0]
  errorNotification(error)

  return (
    <div className="App">
      <Router>
        <Switch>
          <Route path='/kashite/top' render={() => <Top />} />
          <Route path='/kashite/main' render={() => <Auth children={Main} />} />
        </Switch>
      </Router>
    </div >
  );
}

export default App;
