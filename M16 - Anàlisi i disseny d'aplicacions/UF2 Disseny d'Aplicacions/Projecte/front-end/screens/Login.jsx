import { useNavigation } from '@react-navigation/native';
import { Image, Text, View } from "react-native";
import AppleButton from "../components/AppleButton";
import GoogleButton from "../components/GoogleButton";
import { globalStyles } from "../globalStyles";

import lionKing from '../assets/films/lionking.jpg';

export default function Login() {
  const navigation = useNavigation();

  return (
    <View style={globalStyles.container}>
      <Image style={styles.image} source={lionKing} />

      <View style={styles.main}>
        <Text style={styles.text}>RecomendMe</Text>

        <View style={styles.signUp}>
          <Text style={[globalStyles.textBase, styles.signUpText]}>Sign In</Text>
          <Text style={[globalStyles.textBase, styles.signUpTextCreate]}>Please sign in to continue.</Text>
        </View>

        <View style={styles.buttonsSign}>
          <GoogleButton text="Sign in with Google" />
          <AppleButton text="Sign in with Apple" />
        </View>

        <Text style={styles.haveAccount}>
          Don't have an account? Please{" "}
          <Text
            style={styles.loginLink}
            onPress={() => navigation.navigate('Register')}
          >
            Sign up
          </Text>{" "}
          first.
        </Text>
      </View>
    </View>
  );
}

const styles = {
  image: {
    width: 720,
    height: 380,
    transform: [{ translateX: -40 }],
  },

  main: {
    justifyContent: "center",
    alignItems: "center",
    padding: 20,
  },

  text: {
    color: "#fff",
    fontSize: 40,
    fontWeight: "bold",
  },

  signUp: {
    justifyContent: "center",
    alignItems: "center",
    marginTop: 40,
    marginBottom: 40,
  },

  signUpText: {
    fontSize: 30,
    fontWeight: "bold",
  },

  signUpTextCreate: {
    marginTop: 10,
    fontSize: 13,
  },

  buttonsSign: {
    gap: 20,
    marginBottom: 40,
  },

  haveAccount: {
    fontSize: 12,
    color: '#E9A6A6',
  },

  loginLink: {
    color: '#9C4A8B',
  },
};
