/* eslint-disable no-script-url,jsx-a11y/anchor-is-valid */
import React from "react";
import {useLocation} from "react-router";
import {NavLink}  from "react-router-dom";
import SVG from "react-inlinesvg";
import {toAbsoluteUrl, checkIsActive} from "../../../../_helpers";

export function AsideMenuList({ layoutProps }) {
  const location = useLocation();
  const getMenuItemActive = (url, hasSubmenu = false) => {
    return checkIsActive(location, url)
        ? ` ${!hasSubmenu && "menu-item-active"} menu-item-open `
        : "";
  };

  return (
      <>
        {/* begin::Menu Nav */}
        <ul className={`menu-nav ${layoutProps.ulClasses}`}>
          {/* Dashboard */}
          <li
              className={`menu-item ${getMenuItemActive("/dashboard", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Design/Layers.svg")}/>
            </span>
              <span className="menu-text">Dashboard</span>
            </NavLink>
          </li>

          {/* Agreements */}
          <li
              className={`menu-item menu-item-submenu ${getMenuItemActive(
                  "/rdm/agreements", true
              )}`}
              aria-haspopup="true"
              data-menu-toggle="hover"
          >
            <NavLink className="menu-link menu-toggle" to="/rdm/agreements">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Design/Cap-2.svg")}/>
            </span>
              <span className="menu-text">Agreements</span>
              <i className="menu-arrow"/>
            </NavLink>
            <div className="menu-submenu ">
              <i className="menu-arrow"/>
              <ul className="menu-subnav">
                <li className="menu-item  menu-item-parent" aria-haspopup="true">
                <span className="menu-link">
                  <span className="menu-text">Agreements</span>
                </span>
                </li>

                <li
                    className={`menu-item menu-item-submenu ${getMenuItemActive(
                        "/rdm/agreements", true
                    )}`}
                    aria-haspopup="true"
                    data-menu-toggle="hover"
                >
                  <NavLink className="menu-link menu-toggle" to="/rdm/agreements">
                    <i className="menu-bullet menu-bullet-dot">
                      <span/>
                    </i>
                    <span className="menu-text">Rebates</span>
                    <i className="menu-arrow"/>
                  </NavLink>
                </li>

                <li
                    className={`menu-item menu-item-submenu ${getMenuItemActive(
                        "/rdm/agreements", true
                    )}`}
                    aria-haspopup="true"
                    data-menu-toggle="hover"
                >
                  <NavLink className="menu-link menu-toggle" to="/rdm/agreements">
                    <i className="menu-bullet menu-bullet-dot">
                      <span/>
                    </i>
                    <span className="menu-text">Deals</span>
                    <i className="menu-arrow"/>
                  </NavLink>
                </li>

              </ul>
            </div>
          </li>

          {/* Reports */}
          <li
              className={`menu-item ${getMenuItemActive("/reports", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Files/File.svg")}/>
            </span>
              <span className="menu-text">Reports</span>
            </NavLink>
          </li>

          {/* Claims */}
          <li
              className={`menu-item ${getMenuItemActive("/claims", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Shopping/Box2.svg")}/>
            </span>
              <span className="menu-text">Claims</span>
            </NavLink>
          </li>

          {/* Journals */}
          <li
              className={`menu-item ${getMenuItemActive("/journals", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Shopping/Bag2.svg")}/>
            </span>
              <span className="menu-text">Journals</span>
            </NavLink>
          </li>

          {/* Admin */}
          <li
              className={`menu-item ${getMenuItemActive("/admin", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Communication/Add-user.svg")}/>
            </span>
              <span className="menu-text">Admin</span>
            </NavLink>
          </li>

          {/* Support */}
          <li
              className={`menu-item ${getMenuItemActive("/support", false)}`}
              aria-haspopup="true"
          >
            <NavLink className="menu-link" to="#">
            <span className="svg-icon menu-icon">
              <SVG src={toAbsoluteUrl("/media/svg/icons/Communication/Group-chat.svg")}/>
            </span>
              <span className="menu-text">Support</span>
            </NavLink>
          </li>

        </ul>

      </>
  );
}
