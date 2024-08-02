import React, { useRef, useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

// const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

const Register = () => {
  const userRef = useRef();
  const errorRef = useRef();

  const [user, setUser] = useState("");
  const [password, setPwd] = useState("");
  const [errorMsg, setErrMsg] = useState("");
  const [firstname, setFirstname] = useState("");
  const [lastname, setLastname] = useState("");
  const [email, setEmail] = useState("");
  const [bio, setBio] = useState("");

  const nav = useNavigate();

  // useEffect(() => {
  //   setUser(USER_REGEX.test(user));
  // }, [user]);

  // useEffect(() => {
  //   setPwd(PWD_REGEX.test(password));
  // }, [password]);

  useEffect(() => {
    setErrMsg("");
  }, [user, password]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    // const check1 = USER_REGEX.test(user);
    // const check2 = PWD_REGEX.test(password);
    // if (!check1 || !check2) {
    //   setErrMsg("Invalid login information -username needs to be 3-23 characters and password 8-24");
    //   return;
    // }
    try {
      const response = await axios.post(
        "http://localhost:8080/register",
        {
          username: user,
          password: password,
          fname: firstname,
          lname: lastname,
          email: email,
          bio: bio,
        },
        {
          headers: { "Content-Type": "application/json" },
        }
      );
      console.log(response?.data);

      nav("/");
    } catch (err) {
      if (!err?.response) {
        setErrMsg("No Server Response");
      } else {
        setErrMsg("Registration Failed");
      }
    }
  };

  /*
        setUser("");
      setPwd("");
      setFirstname("");
      setLastname("");
      setEmail("");
      setBio("");
  */

  return (
    <section>
      <p
        ref={errorRef}
        className={errorMsg ? "errmsg" : "offscreen"}
        aria-live="assertive"
      >
        {errorMsg}
      </p>
      <h1>Register</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          autoComplete="off"
          onChange={(e) => setUser(e.target.value)}
          required
        />
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          onChange={(e) => setPwd(e.target.value)}
          required
        />
        <br />
        <label htmlFor="firstname">First Name:</label>
        <input
          type="text"
          id="firstname"
          autoComplete="off"
          onChange={(e) => setFirstname(e.target.value)}
          required
        />
        <label htmlFor="lastname">Last Name:</label>
        <input
          type="text"
          id="lastname"
          autoComplete="off"
          onChange={(e) => setLastname(e.target.value)}
          required
        />
        <br />
        <label htmlFor="email">Email Address:</label>
        <input
          type="email"
          id="email"
          autoComplete="off"
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <br />
        <label htmlFor="bio">User Bio:</label>
        <input
          type="text"
          id="bio"
          autoComplete="off"
          onChange={(e) => setBio(e.target.value)}
          required
        />
        <div></div>
        <button type="submit">Register</button>
      </form>
      <p>
        Already have an Account?
        <br />
        <span className="line">
          <a href="/login">Login</a>
        </span>
      </p>
    </section>
  );
};

export default Register;
