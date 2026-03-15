from pydantic import BaseModel


class FirstCycleInput(BaseModel):

    user_id:int
    last_period_start_date:str

    cycle_length_range:str
    period_duration:str
    flow:str
    regularity:str

    age:float
    bmi:float


class AddCycleInput(BaseModel):
    user_id: int
    cycle_length: int
    menses_length: int
    period_start_date: str


class PredictCycleInput(BaseModel):

    user_id:int
    last_period_start_date:str


class PCOSPredictionInput(BaseModel):

    user_id:int

    age:float
    bmi:float

    weight_gain:int
    hair_growth:int
    skin_darkening:int
    hair_loss:int
    pimples:int
    fast_food:int
    exercise:int
