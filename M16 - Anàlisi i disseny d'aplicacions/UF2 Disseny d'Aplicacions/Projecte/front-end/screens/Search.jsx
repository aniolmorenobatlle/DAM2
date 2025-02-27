import { useNavigation } from "@react-navigation/native";
import React from "react";
import { Dimensions, Image, ScrollView, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";

import searchIcon from '../assets/icons/searchIcon.png';

import avatar from '../assets/films/avatar.jpg';
import babyDriver from '../assets/films/babydriver.jpg';
import pulpFiction from '../assets/films/pulpfiction.jpg';
import theBatman from '../assets/films/thebatman.jpg';

const films = [
  { title: "The Batman", image: theBatman },
  { title: "Pulp Fiction", image: pulpFiction },
  { title: "Avatar", image: avatar },
  { title: "Baby Driver", image: babyDriver },
];

// llista 4x8
const repeatedFilms = Array.from({ length: 32 }, (_, i) => films[i % films.length]);

const numColumns = 4;
const screenWidth = Dimensions.get("window").width;
const itemSize = screenWidth / numColumns - 10;

export default function Search() {
  const navigation = useNavigation();

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
              <TouchableOpacity activeOpacity={0.8} onPress={() => navigation.navigate("Film", { title: item.title })}>
                <Image source={item.image} style={styles.movieImage} />
              </TouchableOpacity>
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
