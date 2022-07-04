import i18n from "i18next";
import { initReactI18next } from "react-i18next";

i18n.use(initReactI18next).init({
  resources: {
    en: {
      translations: {
        "Sign Up": "Sign Up",
        "Password mismatched": "Password mismatched",
        Username: "Username",
        "Display Name": "Display Name",
        Password: "Password",
        "Password Repeat": "Password Repeat",
        Login: "Login",
      },
    },
    tr: {
      translations: {
        "Sign Up": "Kayit Ol",
        "Password mismatched": "Sifreler eslesmiyor",
        Username: "Kullanici Adi",
        "Display Name": "Ad",
        Password: "Sifre",
        "Password Repeat": "Sifre Tekrarı",
        Login: "Giris",
      },
    },
  },
  fallbackLng: "tr",
  ns: ["translations"],
  defaultNS: "translations",
  keySeparator: false,
  interpolation: {
    escapeValue: false,
    formatSeparator: ",",
  },
  react: {
    wait: true,
  },
});

export default i18n;
