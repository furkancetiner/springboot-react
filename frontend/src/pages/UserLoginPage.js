import React from "react";
import Input from "../components/Input";
import { withTranslation } from "react-i18next";
import { changeLanguage, login } from "../api/apiCalls";

class UserLoginPage extends React.Component {
  state = {
    username: null,
    password: null,
  };

  onChange = (event) => {
    const { name, value } = event.target;

    this.setState({
      [name]: value,
    });
  };

  onChangeLanguage = (language) => {
    const { i18n } = this.props;
    i18n.changeLanguage(language);
    changeLanguage(language);
  };

  onClickLogin = (event) => {
    event.preventDefault();
    const { username, password } = this.state;
    const creds = {
      username,
      password,
    };
    login(creds);
  };

  render() {
    const { t } = this.props;
    return (
      <div className="container">
        <div className="text-center">
          <h1>{t("Login")}</h1>
        </div>
        <form>
          <div clasName="text-center">
            <Input
              name="username"
              label={t("Username")}
              onChange={this.onChange}
            ></Input>
            <Input
              name="password"
              label={t("Password")}
              type="password"
              onChange={this.onChange}
            ></Input>

            <div className="text-center">
              <button className="btn btn-primary" onClick={this.onClickLogin}>
                {t("Login")}
              </button>
            </div>
          </div>
        </form>
      </div>
    );
  }
}

export default withTranslation()(UserLoginPage);
