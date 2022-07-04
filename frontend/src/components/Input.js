import React from "react";

const Input = (props) => {
  const { name, label, error, onChange, type } = props;
  const className = error ? "form-control is-invalid" : "form-control ";
  return (
    <div className="form-group">
      <label className="form-label">{label}</label>
      <input
        name={name}
        onChange={onChange}
        className={className}
        type={type}
      ></input>
      <div className="invalid-feedback">{error}</div>
    </div>
  );
};

export default Input;
