import { useNavigation } from '@react-navigation/native';
import React from "react";
import { Image, ScrollView, Text, TouchableOpacity, View } from "react-native";
import { SafeAreaView } from "react-native-safe-area-context";
import { globalStyles } from "../globalStyles";


import avatar from '../assets/films/avatar.jpg';
import babyDriver from '../assets/films/babydriver.jpg';
import pulpFiction from '../assets/films/pulpfiction.jpg';
import theBatman from '../assets/films/thebatman.jpg';

import clockIcon from '../assets/icons/clock.png';
import hamburger from '../assets/icons/menu.png';

import roberPattinson from '../assets/actors/robert-pattinson.jpg';

const films = [
  { name: "The Batman", image: theBatman, year: 2022, duration: "175 min" },
  { name: "Pulp Fiction", image: pulpFiction, year: 1994, duration: "154 min" },
  { name: "Avatar", image: avatar, year: 2009, duration: "162 min" },
  { name: "Baby Driver", image: babyDriver, year: 2017, duration: "113 min" },
]

export default function Home() {
  const navigation = useNavigation();
  
  return (
    <SafeAreaView style={[globalStyles.container, styles.mainContainer]}>
      <View style={styles.header}>
        <Image style={styles.menuIcon} source={hamburger} />
        <TouchableOpacity onPress={() => navigation.navigate('Profile')}>
          <Image style={[styles.menuIcon, styles.menuIconAvatar]} source={roberPattinson} />
        </TouchableOpacity>
      </View>

      <ScrollView style={globalStyles.container} showsVerticalScrollIndicator={false}>
        <View style={styles.main}>

          <View>
            <Text style={[globalStyles.textBase, styles.welcomeback]}>
              Hello, <Text style={styles.welcomebackUser}>Robert</Text>!
            </Text>

            <Text style={[globalStyles.textBase, styles.newToday]}>
              See what's new today!  
            </Text>
          </View>

          <View style={styles.latest}>
            <Text style={[globalStyles.textBase, styles.latestTitle]}>Popular this month</Text>

            <ScrollView horizontal={true} showsHorizontalScrollIndicator={false}>
              <View style={styles.latestFilms}>

                {films.map((film, index) => (
                  <View key={index} style={styles.filmsCard}>
                    <TouchableOpacity activeOpacity={0.8} onPress={() => navigation.navigate('Film', { film })}>
                      <Image style={styles.filmCardImage} source={film.image} />
                    </TouchableOpacity>
                    <Text style={[globalStyles.textBase, styles.filmCardName]}>{film.name}</Text>
                  </View>
                ))}

              </View>
            </ScrollView>
          </View>

          <View style={styles.latest}>
            <Text style={[globalStyles.textBase, styles.latestTitle]}>Favorite</Text>

            <View style={styles.favoriteFilms}>
              {films.map((film, index) => (
                <TouchableOpacity key={index} activeOpacity={0.8} onPress={() => navigation.navigate('Film', { film })}>
                <View key={index} style={styles.favoriteCard}>
                  <Image style={styles.favoriteCardImage} source={film.image} /> 
                  <View style={styles.favoriteCardInfo}> 
                    <Text style={[globalStyles.textBase, styles.favoriteCardInfoTitle]}>{film.name}</Text> 
                    <Text style={[globalStyles.textBase, styles.favoriteCardInfoYear]}>{film.year}</Text> 
                    <View style={styles.favoriteCardInfoDuration}> 
                      <Image style={styles.favoriteCardInfoDurationClock} source={clockIcon} />
                      <Text style={[globalStyles.textBase, styles.favoriteCardInfoDurationText]}>{film.duration}</Text> 
                    </View> 
                  </View> 
                </View>
                </TouchableOpacity>
              ))} 
            </View>
          </View>
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
    flexDirection: "row",
    justifyContent: "space-between",
    alignItems: "center",
    paddingBottom: 10,
  },

  menuIcon: {
    width: 50,
    height: 50,
  },

  menuIconAvatar: {
    borderRadius: "50%",
  },

  main: {
    justifyContent: "center",
  },

  welcomeback: {
    fontSize: 25,
    fontWeight: "bold",
    marginTop: 20,
  },

  welcomebackUser: {
    color: "#E9A6A6",
  },

  newToday: {
    fontSize: 15,
    marginTop: 5,
  },

  latest: {
    marginTop: 30,
  },

  latestTitle: {
    fontSize: 22,
    fontWeight: "bold",
    paddingBottom: 10,
  },

  latestFilms: {
    flexDirection: "row",
    justifyContent: "space-between",
    gap: 20,
    height: 260
  },

  filmCardImage: {
    height: 225,
    width: 150,
    borderRadius: 10,
  },

  filmCardName: {
    fontSize: 14,
    marginTop: 10,
  },

  favoriteCard: {
    flexDirection: "row",
    borderRadius: 10,
    backgroundColor: "#2c2942",
    padding: 15,
    paddingLeft: 20,
    borderRadius: 25,
    marginBottom: 10,
  },

  favoriteCardImage: {
    height: 120,
    width: 90,
    borderRadius: 10,
  },

  favoriteCardInfo: {
    flex: 1,
    flexDirection: "column",
    justifyContent: "space-between",
    marginLeft: 15,
  },

  favoriteCardInfoTitle: {
    fontSize: 16,
    fontWeight: "bold",
  },

  favoriteCardInfoYear: {
    fontSize: 14,
    fontWeight: "semibold",
    color: "#aba9b3",
  },

  favoriteCardInfoDuration: {
    flexDirection: "row",
    alignItems: "center",
  },

  favoriteCardInfoDurationClock: {
    width: 20,
    height: 20,
    marginRight: 5,
  },

  favoriteCardInfoDurationText: {
    fontSize: 14,
    fontWeight: "semibold",
  },
};
