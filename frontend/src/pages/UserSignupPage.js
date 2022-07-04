import React from "react";
import axios from "axios";
import { signup, changeLanguage } from "../api/apiCalls";
import Input from "../components/Input";
import { withTranslation } from "react-i18next";
import LanguageSelector from "../components/LanguageSelector";

class UserSignupPage extends React.Component {
  state = {
    username: null,
    displayName: null,
    password: null,
    passwordRepeat: null,
    pendingApiCall: false,
    errors: {},
  };

  onChange = (event) => {
    const { value, name } = event.target;
    const { t } = this.props;
    //... ile this.state.errors'ün kopyasını errors e attık
    const errors = { ...this.state.errors };

    errors[name] = undefined;

    if (name === "password" || name === "passwordRepeat") {
      if (name === "password" && value !== this.state.passwordRepeat) {
        errors.passwordRepeat = t("Password mismatched");
      } else if (name === "passwordRepeat" && value !== this.state.password) {
        errors.passwordRepeat = t("Password mismatched");
      } else {
        errors.passwordRepeat = undefined;
      }
    }

    this.setState({
      [name]: value,
      errors,
    });
  };

  onClickSignup = async (event) => {
    //butona tıklandığında kendiğilinden başka sayfaya geçmesini önlüyor
    event.preventDefault();

    const { username, displayName, password } = this.state;

    const body = {
      username: username,
      displayName: displayName,
      password: password,
    };
    this.setState({
      pendingApiCall: true,
    });

    try {
      const response = await signup(body);
    } catch (error) {
      if (error.response.data.validationErrors) {
        this.setState({
          errors: error.response.data.validationErrors,
        });
      }
    }

    this.setState({
      pendingApiCall: false,
    });
  };

  render() {
    const { pendingApiCall, errors } = this.state;
    const { username, displayName, password, passwordRepeat } = errors;
    const { t } = this.props;
    return (
      <div className="container">
        <form>
          <div>
            <h1 className="text-center">{t("Sign Up")}</h1>
          </div>

          <Input
            name="username"
            error={username}
            label={t("Username")}
            onChange={this.onChange}
          />

          <Input
            name="displayName"
            error={displayName}
            label={t("Display Name")}
            onChange={this.onChange}
          />

          <Input
            name="password"
            error={password}
            label={t("Password")}
            onChange={this.onChange}
            type="password"
          />

          <Input
            name="passwordRepeat"
            error={passwordRepeat}
            label={t("Password Repeat")}
            onChange={this.onChange}
            type="password"
          />

          <div className="text-center">
            <button
              onClick={this.onClickSignup}
              disabled={pendingApiCall || passwordRepeat !== undefined}
              className="btn btn-primary"
            >
              {pendingApiCall && (
                <span className="spinner-border spinner-border-sm text-light"></span>
              )}
              {t("Sign Up")}
            </button>
          </div>
        </form>
      </div>
    );
  }
}

export default withTranslation()(UserSignupPage);
