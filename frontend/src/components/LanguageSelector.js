import React from "react";
import { changeLanguage } from "../api/apiCalls";
import { withTranslation } from "react-i18next";

const LanguageSelector = (props) => {
  const onChangeLanguage = (language) => {
    const { i18n } = props;
    i18n.changeLanguage(language);
    changeLanguage(language);
  };

  return (
    <div className="container">
      <div>
        <img src="" alt="Turkish" onClick={() => onChangeLanguage("tr")}></img>
        <img src="" alt="English" onClick={() => onChangeLanguage("en")}></img>
      </div>
    </div>
  );
};

export default withTranslation()(LanguageSelector);
