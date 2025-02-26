import React, { useState } from "react";
import { Dimensions, Image, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";

import avatar from '../assets/films/avatar.jpg';
import babyDriver from '../assets/films/babydriver.jpg';
import pulpFiction from '../assets/films/pulpfiction.jpg';
import theBatman from '../assets/films/thebatman.jpg';

import watchLogo from '../assets/icons/eye.png';
import likeLogo from '../assets/icons/heart.png';
import rateLogo from '../assets/icons/star.png';
import wathlistLogo from '../assets/icons/watchlist.png';

import watchLogoActive from '../assets/icons/eyeActive.png';
import likeLogoActive from '../assets/icons/heartActive.png';
import rateLogoActive from '../assets/icons/starActive.png';
import wathlistLogoActive from '../assets/icons/watchlistActive.png';

import Carousel from 'react-native-reanimated-carousel';

const marks = [
  { name: "Watch", image: watchLogo, activeImage: watchLogoActive },
  { name: "Like", image: likeLogo, activeImage: likeLogoActive },
  { name: "Rate", image: rateLogo, activeImage: rateLogoActive },
  { name: "Watchlist", image: wathlistLogo, activeImage: wathlistLogoActive },
];

const films = [
  { title: "Baby Driver", year: "2017", image: babyDriver },
  { title: "Avatar", year: "2009", image: avatar },
  { title: "Pulp Fiction", year: "1994", image: pulpFiction },
  { title: "The Batman", year: "2022", image: theBatman },
];

const { width } = Dimensions.get("window");
const carouselWidth = width - 30;

export default function Recommend() {
  const [activeMarks, setActiveMarks] = useState({});

  const handlePress = (name) => {
    setActiveMarks((prev) => ({
      ...prev,
      [name]: !prev[name],
    }));
  };

  return (
    <SafeAreaView style={[globalStyles.container, styles.mainContainer]}>
      <Carousel
        width={width}
        height={570}
        data={films}
        
        renderItem={({ index }) => (
          <View style={[styles.header, { width: carouselWidth }]}>
            <View style={styles.filmInfo}>
              <Text style={[globalStyles.textBase, styles.headerTitle]}>
                {films[index].title}
              </Text>
              <Text style={[globalStyles.textBase, styles.headerYear]}>
                {films[index].year}
              </Text>
            </View>
            <Image style={styles.filmImage} source={films[index].image} />
          </View>
        )}
      />

      <View style={styles.marks}>
        {marks.map((mark, index) => (
          <View key={index} style={styles.mark}>
            <TouchableOpacity onPress={() => handlePress(mark.name)}>
              <Image
                style={styles.markImage}
                source={activeMarks[mark.name] ? mark.activeImage : mark.image}
              />
            </TouchableOpacity>
            <Text style={[globalStyles.textBase, styles.markName]}>{mark.name}</Text>
          </View>
        ))}
      </View>
    </SafeAreaView>
  );
}

const styles = {
  mainContainer: {
    paddingHorizontal: 15,
    paddingTop: 10,
  },

  header: {
    flexDirection: "column",
    alignItems: "center",
    gap: 10,
  },

  filmInfo: {
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    width: "100%",
  },

  headerTitle: {
    fontSize: 18,
  },

  headerYear: {
    fontSize: 18,
  },

  filmImage: {
    width: "100%",
    height: 520,
    borderRadius: 10,
    marginTop: 10,
    borderWidth: 1,
    borderColor: "#D3D3D3",
  },

  marks: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 30,
  },

  mark: {
    flexDirection: "column",
    alignItems: "center",
    gap: 10,
  },

  markImage: {
    height: 50,
    width: 50,
  },

  markName: {
    fontSize: 14,
  },
};
