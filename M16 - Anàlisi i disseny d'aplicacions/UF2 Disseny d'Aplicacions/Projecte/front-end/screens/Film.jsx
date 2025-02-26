import React from "react";
import { Image, ImageBackground, ScrollView, Text, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";

import thebatmanBackground from '../assets/films/thebatman-background.jpg';
import thebatmanPoster from '../assets/films/thebatman-poster.jpg';

export default function Film() {
  return (
    <ScrollView vertical={true} showsVerticalScrollIndicator={false}>
      <ImageBackground source={thebatmanBackground} style={styles.imageBackground}>
        <SafeAreaView style={styles.overlay}>
          <View style={styles.backButton}>
            <Text style={styles.backArrow}>←</Text>
          </View>
        </SafeAreaView>
      </ImageBackground>

      <SafeAreaView style={[globalStyles.container, styles.mainContainer]}>
        <View style={styles.filmHeader}>
          <Image style={styles.poster} source={thebatmanPoster} />

          <View style={styles.textContainer}>
            <Text style={[globalStyles.textBase, styles.filmTitle]}>
              The Batman <Text style={styles.filmYear}>2022</Text>
            </Text>
            <Text style={[globalStyles.textBase, styles.filmDirector]}>
              Directed by <Text style={{ fontWeight: "bold" }}>Matt Reeves</Text>
            </Text>
          </View>
        </View>
      </SafeAreaView>
    </ScrollView>
  );
}

const styles = {
  imageBackground: {
    height: 320,
    width: '100%',
    justifyContent: 'flex-start',
  },

  overlay: {
    flex: 1,
    justifyContent: 'center',
    paddingHorizontal: 15,
  },

  backButton: {
    backgroundColor: "rgba(0, 0, 0, 0.5)",
    width: 40,
    height: 40,
    borderRadius: 20,
    justifyContent: "center",
    alignItems: "center",
    marginTop: 20,
  },

  backArrow: {
    color: "#fff",
    fontSize: 24,
  },

  mainContainer: {
    paddingHorizontal: 15,
    paddingTop: 10,
  },

  filmHeader: {
    flexDirection: "row",
    alignItems: "center",
  },

  poster: {
    width: 130,
    height: 190,
    borderRadius: 10,
    marginTop: -120,
  },

  textContainer: {
    marginLeft: 20,
    flex: 1,
  },

  filmTitle: {
    fontSize: 22,
    fontWeight: "bold",
    color: "#fff",
  },

  filmYear: {
    fontSize: 14,
    fontWeight: "normal",
    color: "#aaa",
  },

  filmDirector: {
    fontSize: 12,
    marginTop: 5,
  },
};
