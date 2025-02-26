import React from "react";
import { Dimensions, Image, ScrollView, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";

import searchIcon from '../assets/icons/searchIcon.png';

import avatar from '../assets/films/avatar.jpg';
import babyDriver from '../assets/films/babydriver.jpg';
import pulpFiction from '../assets/films/pulpfiction.jpg';
import theBatman from '../assets/films/thebatman.jpg';

const films = [
  { name: "The Batman", image: theBatman },
  { name: "Pulp Fiction", image: pulpFiction },
  { name: "Avatar", image: avatar },
  { name: "Baby Driver", image: babyDriver },
];

// Generar llista 4x8
const repeatedFilms = Array.from({ length: 32 }, (_, i) => films[i % films.length]);

const numColumns = 4;
const screenWidth = Dimensions.get("window").width;
const itemSize = screenWidth / numColumns - 10;

export default function Search() {
  return (
    <SafeAreaView style={[globalStyles.container, styles.mainContainer]}>
      <View style={styles.header}>
        <Text style={[globalStyles.textBase, styles.headerTitle]}>Search</Text>

        <View style={styles.searchBar}>
          <Image style={styles.searchIcon} source={searchIcon} />
          <Text style={[globalStyles.textBase, styles.searchText]}>Search for a movie</Text>
        </View>
      </View>

      <ScrollView contentContainerStyle={styles.scrollContainer} showsVerticalScrollIndicator={false}>
        <Text style={[globalStyles.textBase, styles.popularTitle]}>Most popular</Text>

        <View style={styles.movieGrid}>
          {repeatedFilms.map((item, index) => (
            <View key={index} style={[styles.movieItem, { width: itemSize, height: itemSize * 1.5 }]}>
              <Image source={item.image} style={styles.movieImage} />
            </View>
          ))}
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = {
  mainContainer: {
    paddingHorizontal: 15,
  },

  header: {
    alignItems: "center",
    paddingBottom: 10,
  },

  headerTitle: {
    fontSize: 25,
    fontWeight: "bold",
  },

  searchBar: {
    width: "100%",
    height: 40,
    backgroundColor: "#fff",
    borderRadius: 10,
    marginTop: 10,
    justifyContent: "center",
  },

  searchIcon: {
    width: 20,
    height: 20,
    position: "absolute",
    left: 10,
  },

  searchText: {
    color: "#c3c3c3",
    fontSize: 15,
    marginLeft: 40,
  },

  popularTitle: {
    fontSize: 18,
    paddingBottom: 10,
    marginTop: 10,
  },

  movieGrid: {
    flexDirection: "row",
    flexWrap: "wrap",
    justifyContent: "space-between",
  },

  movieItem: {
    alignItems: "center",
  },

  movieImage: {
    width: 80,
    height: 120,
    borderRadius: 10,
  },
};
