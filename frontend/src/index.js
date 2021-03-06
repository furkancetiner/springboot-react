import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import "./bootstrap-override.scss";
import UserSignupPage from "./pages/UserSignupPage";
import reportWebVitals from "./reportWebVitals";
import "./i18n";
import UserLoginPage from "./pages/UserLoginPage";
import LanguageSelector from "./components/LanguageSelector";

ReactDOM.render(
  <React.StrictMode>
    <UserSignupPage />
    <LanguageSelector />
  </React.StrictMode>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
