/* 
 * Copyright (C) 2017 Laboratory of Experimental Biophysics
 * Ecole Polytechnique Federale de Lausanne
 * 
 * Author: Marcel Stefko
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.epfl.leb.alica.lasers;

import ch.epfl.leb.alica.Laser;
import org.micromanager.Studio;

/**
 * A virtual laser which does not actually output the values to the laser,
 * only to the GUI and the debug MM log.
 * @author Marcel Stefko
 */
public class VirtualLaser implements Laser {
    private final Studio studio;
    
    private final String device_name;
    private final String property_name;
    private final double min_power;
    private final double max_power;
    private double current_power_cached = 0.0;
    
    /**
     * Initialize the virtual laser
     * @param studio MMStudio
     * @param device_name MM identifier of the device
     * @param property_name MM identifier of the property to be controlled
     * @param min_power minimal allowed property value
     * @param max_power maximal allowed property value
     */
    public VirtualLaser(Studio studio, String device_name, String property_name, 
            double min_power, double max_power) {
        this.studio = studio;
        this.device_name = device_name;
        this.property_name = property_name;
        this.min_power = min_power;
        this.max_power = max_power;
    }

    @Override
    public double setLaserPower(double desired_power) throws Exception {
        // constrain the input value
        double actual_power;
        if (desired_power > max_power) {
            actual_power = max_power;
        } else if (desired_power < min_power) {
            actual_power = min_power;
        } else {
            actual_power = desired_power;
        }
        
        if (current_power_cached == actual_power) {
            // do nothing
            return current_power_cached;
        }
        
        current_power_cached = actual_power;
        studio.logs().logMessage(String.format("Virtual: Setting power to: %8.4f", actual_power));
        return actual_power;
    }

    @Override
    public double getLaserPower() throws Exception {
        current_power_cached = Double.parseDouble(studio.core().getProperty(device_name, property_name));
        return current_power_cached;
    }
    
    public double getLaserPowerCached() {
        return current_power_cached;
    }

    @Override
    public double getMaxPower() {
        return max_power;
    }

    @Override
    public double getMinPower() {
        return min_power;
    }

    @Override
    public String getDeviceName() {
        return "V:"+device_name;
    }

    @Override
    public String getPropertyName() {
        return property_name;
    }
}
