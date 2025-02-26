import { Image, Text, View } from 'react-native';

import googleLogo from '../assets/logos/googleLogo.png';

export default function GoogleButton({ text }) {
  return (
    <View style={styles.mainButton}>
      <Image
        style={styles.logo}
        source={googleLogo}
      />        
      <Text style={styles.text}>{ text }</Text>
    </View>
  );
}

const styles = {
  mainButton: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "center",
    width: 350,
    height: 45,
    backgroundColor: "#fff",
    borderRadius: 20,
  },

  logo: {
    width: 25,
    height: 25,
    marginLeft: 10,
  },

  text: {
    flex: 1,
    textAlign: "center",
    fontSize: 14,
  }
}
