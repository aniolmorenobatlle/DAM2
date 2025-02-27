import { useNavigation } from "@react-navigation/native";
import React from "react";
import { Image, TouchableOpacity, View } from "react-native";

import bell from '../assets/icons/bell.png';
import home from '../assets/icons/home.png';
import recomendIcon from '../assets/icons/recomendIcon.png';
import search from '../assets/icons/search.png';
import userNav from '../assets/icons/userNav.png';

import bellActive from '../assets/icons/bellActive.png';
import homeActive from '../assets/icons/homeActive.png';
import searchActive from '../assets/icons/searchActive.png';
import userNavActive from '../assets/icons/userNavActive.png';

export default function Navbar({ currentPage }) {
  const navigation = useNavigation();

  const handleIconClick = (page) => {
    navigation.navigate(page);
  };

  return (
    <View style={styles.navbar}>
      <TouchableOpacity onPress={() => handleIconClick('Home')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'Home' ? homeActive : home}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('Search')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'Search' ? searchActive : search}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('Recommend')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'Recommend' ? recomendIcon : recomendIcon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('Notifications')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'Notifications' ? bellActive : bell}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('Profile')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'Profile' ? userNavActive : userNav}
        />
      </TouchableOpacity>
    </View>
  );
}

const styles = {
  navbar: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    height: 80,
    width: "100%",
    backgroundColor: "#1F1D36",
    paddingHorizontal: 30,
    paddingBottom: 30,
    borderTopWidth: 1,
    borderTopColor: "#2A2634",
  },

  navbarIcon: {
    width: 30,
    height: 30,
  }
};
