import random
import numpy
import time

def measure_execution_times(func, n=1000):
    times = []
    for _ in range(n):
        start_time = time.perf_counter()  # High-precision timer
        func()
        end_time = time.perf_counter()
        times.append(end_time - start_time)

    return {
        "min_time": min(times),
        "max_time": max(times),
        "avg_time": sum(times) / len(times),
        "all_times": times,  # Optional: return all times for analysis
    }

random_integers = [random.randint(0, 100) for _ in range(10**6)]
random_floats = [random.uniform(0, 10) for _ in range(10**6)]
random_numpy_integers = numpy.array(random_integers)
random_numpy_floats = numpy.array(random_floats)

def int_default_summing():
    return sum(random_integers)

def float_default_summing():
    return sum(random_floats)

def int_numpy_summing():
    return numpy.sum(random_numpy_integers)

def float_numpy_summing():
    return numpy.sum(random_numpy_floats)

int_default_summing_results = measure_execution_times(int_default_summing)
print("int_default_summing_results")
print(f"min_time: {int_default_summing_results['min_time']*1000:.2f} ms")
print(f"avg_time: {int_default_summing_results['avg_time']*1000:.2f} ms")
print(f"max_time: {int_default_summing_results['max_time']*1000:.2f} ms")
print()

float_default_summing_results = measure_execution_times(float_default_summing)
print("float_default_summing_results")
print(f"min_time: {float_default_summing_results['min_time']*1000:.2f} ms")
print(f"avg_time: {float_default_summing_results['avg_time']*1000:.2f} ms")
print(f"max_time: {float_default_summing_results['max_time']*1000:.2f} ms")
print()

int_numpy_summing_results = measure_execution_times(int_numpy_summing)
print("int_numpy_summing_results")
print(f"min_time: {int_numpy_summing_results['min_time']*1000:.2f} ms")
print(f"avg_time: {int_numpy_summing_results['avg_time']*1000:.2f} ms")
print(f"max_time: {int_numpy_summing_results['max_time']*1000:.2f} ms")
print()

float_numpy_summing_results = measure_execution_times(float_numpy_summing)
print("float_numpy_summing_results")
print(f"min_time: {float_numpy_summing_results['min_time']*1000:.2f} ms")
print(f"avg_time: {float_numpy_summing_results['avg_time']*1000:.2f} ms")
print(f"max_time: {float_numpy_summing_results['max_time']*1000:.2f} ms")
print()
