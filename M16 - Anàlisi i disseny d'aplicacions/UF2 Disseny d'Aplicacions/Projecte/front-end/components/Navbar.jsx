import { useNavigation } from '@react-navigation/native';
import React, { useState } from "react";
import { Image, TouchableOpacity, View } from "react-native";

import bell from '../assets/icons/bell.png';
import home from '../assets/icons/home.png';
import recomendIcon from '../assets/icons/recomendIcon.png';
import search from '../assets/icons/search.png';
import userNav from '../assets/icons/userNav.png';

import bellActive from '../assets/icons/bellActive.png';
import homeActive from '../assets/icons/homeActive.png';
import recomendIconActive from '../assets/icons/recomendIconActive.png';
import searchActive from '../assets/icons/searchActive.png';
import userNavActive from '../assets/icons/userNavActive.png';

export default function Navbar() {
  const [currentPage, setCurrentPage] = useState('home');
  const navigation = useNavigation();

  const handleIconClick = (page) => {
    setCurrentPage(page);
    if (page === 'home') {
      navigation.navigate('Home');
    } else if (page === 'search') {
      navigation.navigate('Search');
    } else if (page === 'recommend') {
      navigation.navigate('Recommend');
    } else if (page === 'notifications') {
      navigation.navigate('Notifications');
    } else if (page === 'user') {
      navigation.navigate('User');
    }
  };

  return (
    <View style={styles.navbar}>
      <TouchableOpacity onPress={() => handleIconClick('home')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'home' ? homeActive : home}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('search')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'search' ? searchActive : search}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('recommend')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'recommend' ? recomendIconActive : recomendIcon}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('notifications')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'notifications' ? bellActive : bell}
        />
      </TouchableOpacity>

      <TouchableOpacity onPress={() => handleIconClick('user')}>
        <Image
          style={styles.navbarIcon}
          source={currentPage === 'user' ? userNavActive : userNav}
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
}
