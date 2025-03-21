import React from "react";
import { Image, ScrollView, Text, View } from 'react-native';
import { globalStyles } from "../globalStyles";

import theBatmanBackground from '../assets/films/thebatman-background.jpg';

import roberPattinson from '../assets/actors/robert-pattinson.jpg';

import avatar from '../assets/films/avatar.jpg';
import babyDriver from '../assets/films/babydriver.jpg';
import pulpFiction from '../assets/films/pulpfiction.jpg';
import thebatman from '../assets/films/thebatman.jpg';

const lists = [
  { title: 'Watchlist', number: 10 },
  { title: 'Watched', number: 30 },
  { title: 'Films', number: 330 },
  { title: 'Reviews', number: 30 },
  { title: 'Likes', number: 302 },
  { title: 'Friends', number: 5 },
]

export default function Profile() {
  return (
    <ScrollView showsVerticalScrollIndicator={false} style={[globalStyles.container, styles.mainContainer]}>
      <Image source={theBatmanBackground} style={styles.backgroundImage} />
      <View style={styles.contentContainer}>
        <View style={styles.avatarContainer}>
          <Image style={styles.avatar} source={roberPattinson}/>
          <Text style={[globalStyles.textBase, styles.name]}>Robert</Text>
          <Text style={[globalStyles.textBase, styles.username]}>@robertpattinson</Text>
        </View>

        <View style={styles.stats}>
          <View style={styles.statsContainer}>
            <Text style={[globalStyles.textBase, styles.statsTotalFilmsNumber]}>445</Text>
            <Text style={[globalStyles.textBase, styles.statsTotalFilms]}>Total Films</Text>
          </View>
          <View style={styles.statsContainer}>
            <Text style={[globalStyles.textBase, styles.statsTotalFilmsYearNumber]}>33</Text>
            <Text style={[globalStyles.textBase, styles.statsTotalFilms]}>Films This Year</Text>
          </View>
          <View style={styles.statsContainer}>
            <Text style={[globalStyles.textBase, styles.statsTotalReviewNumber]}>30</Text>
            <Text style={[globalStyles.textBase, styles.statsTotalFilms]}>Review</Text>
          </View>
        </View>

        <View style={styles.favoritesContainer}>
          <Text style={[globalStyles.textBase, styles.favoritesTitle]}>Robert's Favorite Films</Text>

          <View style={styles.favorites}>
            <Image style={styles.favoritesImage} source={thebatman}/>
            <Image style={styles.favoritesImage} source={avatar}/>
            <Image style={styles.favoritesImage} source={pulpFiction}/>
            <Image style={styles.favoritesImage} source={babyDriver}/>
          </View>
        </View>

        <View style={styles.line}></View>

        <View style={styles.listInfo}>
          <View style={styles.listInfoContainer} contentContainerStyle={{ alignItems: 'center' }}>
            {lists.map((list, index) => (
              <View key={index} style={{ width: '100%' }}>
                <View style={{ flexDirection: 'row', justifyContent: 'space-between', width: '100%' }}>
                  <Text style={[globalStyles.textBase, styles.listInfoTitle]}>{list.title}</Text>
                  <Text style={[globalStyles.textBase, styles.listInfoNumber]}>{list.number}</Text>
                </View>

                <View style={{ width: '100%', height: 1, backgroundColor: "rgba(255, 255, 255, 0.1)", marginVertical: 10 }}></View>
              </View>
            ))}
          </View>
        </View>
      </View>
    </ScrollView>
  );
}

const styles = {
  mainContainer: {
    flex: 1,
  },

  backgroundImage: {
    flex: 1,
    justifyContent: 'center',
    height: 250,
    width: "100%",
  },

  contentContainer: {
    paddingHorizontal: 15,
    marginTop: 60,
  },

  avatarContainer: {
    justifyContent: 'center',
    alignItems: 'center',
    position: 'relative',
  },

  avatar: {
    width: 120,
    height: 120,
    borderRadius: "50%",
    borderWidth: 2,
    borderColor: 'white',
    position: 'absolute',
    top: -120,
  },

  name: {
    fontSize: 25,
    fontWeight: 'bold',
    marginVertical: 5
  },

  username: {
    fontSize: 12,
  },

  stats: {
    marginVertical: 20,
    flexDirection: 'row',
    justifyContent: 'space-around',
    gap: 30,
  },

  statsContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },

  statsTotalFilmsNumber: {
    fontSize: 30,
    color: "#E9A6A6",
    fontWeight: 'bold',
  },

  statsTotalFilmsYearNumber: {
    fontSize: 30,
    color: "#9C4A8B",
    fontWeight: 'bold',

  },

  statsTotalReviewNumber: {
    fontSize: 30,
    color: "#9C4A8B",
    fontWeight: 'bold',
  },

  statsTotalFilms: {
    fontSize: 12,
  },

  favoritesContainer: {
    alignItems: 'center',
    paddingHorizontal: 15,
  },

  favoritesTitle: {
    fontSize: 20,
    fontWeight: 'bold',
  },

  favorites: {
    flexDirection: 'row',
    marginVertical: 10,
    gap: 10,
    justifyContent: 'space-between',
  },

  favoritesImage: {
    width: 82,
    height: 130,
    borderRadius: 10,
  },

  line: {
    height: 1,
    backgroundColor: "rgba(255, 255, 255, 0.5)",
    marginVertical: 20,
    marginHorizontal: -15,
  },

  shortLine: {
    height: 1,
    backgroundColor: "rgba(255, 255, 255, 0.1)",
    marginVertical: 10,
  },

  listInfoNumber: {
    color: "#9C4A8B",
  }
};
