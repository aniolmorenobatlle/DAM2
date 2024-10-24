import multiprocessing


def process1():
    fn = multiprocessing.current_process().name
    print(f"\t Hello, I'm the process: {fn}")


if __name__ == '__main__':
    pn = multiprocessing.current_process().name
    print(f"\t Hello, I'm the process: {pn}")

    np = multiprocessing.Process(target=process1)
    np.start()