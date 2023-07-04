import React, { useState } from "react";
import { Map } from "../SubComponents/Map";
import { NavLink } from "react-router-dom";

export const AddLocation = () => {
  const userType = "Admin";
  const [houseName, setHouseName] = useState([]);
  const [locationName, setLocationName] = useState([]);
  const [coordinates, setCoordinates] = useState([]);

  async function submitLocation(e) {
    e.preventDefault()
    const body = {
      houseName: houseName,
      locationName: locationName,
      coordinates: coordinates,
    };
    const response = await fetch("http://localhost:8090/house/addHouse", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(body),
    });
    setHouseName('')
    setLocationName('')
    setCoordinates('')
    const data = await response.json();
    console.log(data);
  }

  return (
    <>
    <nav
    className="navbar navbar-light navbar-expand-md fixed-top navbar-shrink py-3"
    id="mainNav"
  >
    <div className="container">
      <NavLink className="navbar-brand d-flex align-items-center" to="/">
        <span>Konutum</span>
      </NavLink>
      <button
        data-bs-toggle="collapse"
        className="navbar-toggler"
        data-bs-target="#navcol-1"
      >
        <span className="visually-hidden">Toggle navigation</span>
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navcol-1">
        <ul className="navbar-nav mx-auto">
          <li className="nav-item">
            <NavLink className="nav-link" to="/addLocation">
              Lokasyon Ekle
            </NavLink>
          </li>
          <li className="nav-item"></li>
          <li className="nav-item"></li>
          <li className="nav-item">
            <NavLink className="nav-link" to="/changesAccept">
              Uzman Yorumları
            </NavLink>
          </li>
        </ul>
        <NavLink
          className="btn btn-danger shadow"
          role="button"
          to="/login"
        >
          Çıkış Yap
        </NavLink>
      </div>
    </div>
  </nav>
    <section className="py-5 mt-5">
      <div className="container py-4 py-xl-5">
        <div className="row gy-4 gy-md-0">
          <div className="col-md-3 text-center text-md-start d-flex d-sm-flex d-md-flex justify-content-center align-items-center justify-content-md-start align-items-md-center justify-content-xl-center">
            <div
              style={{
                maxWidth: "380px",
              }}
              className="shadow-lg p-3 mb-5 bg-white rounded"
            >
              <form style={{ textAlign: "center", maxWidth: "360px" }} onSubmit={submitLocation}>
                <p className="fs-3">Konum Ekle</p>
                <input
                  className="form-control"
                  type="text"
                  style={{ marginBottom: "12px" }}
                  placeholder="Konut İsmi"
                  onChange={(e) => {
                    setHouseName(e.target.value);
                  }}
                  value={houseName}
                />
                <input
                  className="form-control"
                  type="text"
                  style={{ marginBottom: "12px" }}
                  placeholder="Lokasyon İsmi"
                  onChange={(e) => {
                    setLocationName(e.target.value);
                  }}
                  value={locationName}
                />
                <div className="input-group" style={{ marginBottom: "12px" }}>
                  <input
                    className="form-control w-100 mb-3"
                    type="text"
                    placeholder="Koordinatlar"
                    onChange={(e) => {
                      setCoordinates(e.target.value);
                    }}
                    value={coordinates}
                  />
                </div>
                <input
                  className="btn btn-primary"
                  type="submit"
                  value={"Kaydet"}
                />
              </form>
            </div>
          </div>
          <div className="col-md-9">
            <div>
              <Map userType={userType} ></Map>
            </div>
          </div>
        </div>
      </div>
    </section>
    </>
  );
};
